from datetime import datetime

from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient

app = Flask(__name__)

client = MongoClient("mongodb://localhost:27017/")
db = client.test


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/detail/<idx>')
def detail(idx):
    # todo
    return render_template('detail.html')


# 게시글 가져오기
@app.route('/articleList', methods=['GET'])
def get_article_list():
    article_list = list(db.weeklytest2.find({}, {'_id': False}))

    for article in article_list:
        article['reg_date'] = article['reg_date'].strftime('%Y.%m.%d %H:%M:%S')

    return jsonify({"article_list": article_list})


# Create
@app.route('/article', methods=['POST'])
def create_article():
    # todo
    # idx, title, content, pw, read_count, reg_data

    article_count = db.weeklytest2.estimated_document_count({})  # DB에 저장되어 있는 갯수 반환
    # 1. 클라이언트에서 넘어오는 값 받기
    title_receive = request.form['title']
    content_receive = request.form['content']
    pw_receive = request.form['pw']

    if article_count == 0:
        idx = 1
    else:
        idx = (list(db.weeklytest2.find({}).sort([("idx", -1)])))[0]['idx'] + 1

    # 2. DB저장 위해 딕셔너리로 만들기
    doc = {
        'idx': idx,
        'title': title_receive,
        'content': content_receive,
        'pw': pw_receive,
        'read_count': 0,
        'reg_date': datetime.now()
    }
    # 3. DB에 저장
    db.weeklytest2.insert_one(doc)

    return {"result": "success"}


# Read
@app.route('/article', methods=['GET'])
def read_article():
    # 1. 클라이언트에서 넘어온 idx값을 받는다.
    idx = request.args.get('idx')

    # 2. DB에서 해당하는 idx값의 데이터를 가져온다
    article = db.weeklytest2.find_one({'idx': idx})

    # 3. 가져온 조회수를 업데이트
    current_count = article['read_count']
    current_count += 1
    db.users.update_one({'idx': idx}, {'$set': {'read_count': current_count}})

    # 4. 가져온 데이터를 클라이언트에게 전달
    return jsonify({"article": article})


# Update
@app.route('/article', methods=['PUT'])
def update_article():
    # todo
    return {"result": "success"}


# Delete
@app.route('/article', methods=['DELETE'])
def delete_article():
    # todo
    return {"result": "success"}


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
