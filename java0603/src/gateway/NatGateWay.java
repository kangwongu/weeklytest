package gateway;

import subnet.Subnet;

public class NatGateWay extends GateWay {
    private Subnet subnet;

    public NatGateWay(Subnet subnet) {
        this.subnet = subnet;
    }

    public Subnet getSubnet() {
        return subnet;
    }

    public void setSubnet(Subnet subnet) {
        this.subnet = subnet;
    }

    @Override
    public void send() {
        System.out.println("NatGateWay send!");
    }
}
