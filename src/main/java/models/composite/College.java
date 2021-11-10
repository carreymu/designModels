package models.composite;

import sun.plugin.liveconnect.OriginNotAllowedException;

import java.util.ArrayList;
import java.util.List;

public class College extends OrganizationComponent {
    // List中存放Department
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public College(String name, String des) {
        super(name, des);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }

    @Override
    protected void print() {
        System.out.println("------" + getName() + "-------");
        organizationComponents.stream().forEach(x -> System.out.println(x.getName() + ">" + x.getDes()));
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
