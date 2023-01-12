package com.hexagon.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class Id {

    private final UUID uuid;

    public Id(UUID uuid){
        this.uuid = uuid;
    }

    public Id(String uuid){
        /// Manually convert since converter package doesn't work
        this.uuid = UUID.fromString(uuid.split(",")[1]);
    }

    public static Id withId(String id){
        return new Id(UUID.fromString(id));
    }

    public static Id withoutId(){
        return new Id(UUID.randomUUID());
    }
}
