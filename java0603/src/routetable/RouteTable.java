package routetable;

import gateway.GateWay;
import subnet.Subnet;

import java.util.ArrayList;
import java.util.List;

public class RouteTable {
    // 필드
    private int id;
    private GateWay gateWay;
    private List<Subnet> subnetList;

    // 생성자
    public RouteTable() {
        subnetList = new ArrayList<>();
    }

    // Getter/Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GateWay getGateWay() {
        return gateWay;
    }

    public void setGateWay(GateWay gateWay) {
        this.gateWay = gateWay;
    }

    public List<Subnet> getSubnetList() {
        return subnetList;
    }

    public void setSubnetList(List<Subnet> subnetList) {
        this.subnetList = subnetList;
    }

    // 서브넷 추가 메소드
    public void addSubnet(Subnet subnet) {
        subnetList.add(subnet);
    }

    public void route() {
        System.out.println("route!");
    }
}
