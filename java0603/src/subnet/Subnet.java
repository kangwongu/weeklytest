package subnet;

import routetable.RouteTable;

public class Subnet {
    // 필드
    private int id;
    private String region;
    private String ipAddress;
    private RouteTable routeTable;

    // 생성자
    public Subnet(int id, String ipAddress, String region) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.region = region;
    }

    // Getter/Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public RouteTable getRouteTable() {
        return routeTable;
    }

    public void setRouteTable(RouteTable routeTable) {
        this.routeTable = routeTable;
    }

    // 외부로 메세지 출력
    public void transfer(String msg) {
        System.out.println(msg);
    }
}
