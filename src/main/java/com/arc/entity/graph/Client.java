package com.arc.entity.graph;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(name = Client.ACCOUNTS_GRAPH,
            attributeNodes = @NamedAttributeNode("account"))
})
public class Client {
    public static final String ACCOUNTS_GRAPH = "Client[accounts]";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_client")
    private Integer id;

    private String name;
    private int age;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts = new ArrayList<>();
}
