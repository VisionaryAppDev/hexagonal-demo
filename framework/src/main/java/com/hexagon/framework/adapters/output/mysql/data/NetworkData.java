package com.hexagon.framework.adapters.output.mysql.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "networks")
public class NetworkData {

    @ManyToOne
    @JoinColumn(name="switch_id")
    private SwitchData switchData;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="network_id")
    private int id;

    @AttributeOverrides({
            @AttributeOverride(
                    name = "address",
                    column = @Column(
                            name = "network_address")),
            @AttributeOverride(
                    name = "protocol",
                    column = @Column(
                            name = "network_protocol")),
    })
    IPData ip;

    @Column(name="network_name")
    String name;

    @Column(name="network_cidr")
    Integer cidr;

    public NetworkData(IPData ip, String name, Integer cidr) {
        this.ip = ip;
        this.name = name;
        this.cidr = cidr;
    }
}
