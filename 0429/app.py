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
    return render_template('detail.html', idx=idx)


# 생성
@app.route('/post', methods=['POST'])
def save_post():
    # // todo
    # idx, title, content, pw, reg_date

    idx_receive = 1
    title_receive = request.form['title_give']
    content_receive = request.form['content_give']
    pw_receive = request.form['pw_give']
    reg_date_receive = datetime.now()

    # 딕셔너리로 만들기
    doc = {
        'idx': idx_receive,
        'title': title_receive,
        'content': content_receive,
        'pw': pw_receive,
        'reg_date': reg_date_receive
    }

    # 데이터 저장
    db.weeklytest1.insert_one(doc)

    return jsonify({'msg': '저장 완료!'})


@app.route('/post', methods=['GET'])
def get_post():
    posts = 0
    # // todo
    datas = list(db.weeklytest1.find({}, {'_id': False}))

    return jsonify({"posts": posts, "all_datas":datas})


@app.route('/post', methods=['DELETE'])
def delete_post():
    idx = request.args.get('idx')
    db.test.delete_one({'idx': int(idx)})
    return {"result": "success"}


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
