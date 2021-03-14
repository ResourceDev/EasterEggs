package org.explorersbay.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class EggReward {

    @Getter @Setter private double chance;
    @Getter @Setter private List<String> commands;

    public EggReward(double chance, List<String> commands) {
        this.chance = chance;
        this.commands = commands;
    }

}
