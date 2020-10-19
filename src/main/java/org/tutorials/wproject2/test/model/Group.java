package org.tutorials.wproject2.test.model;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;

public class Group extends GroupIn{

    @NotNull
    private Long gid;

    public Group() {
        super();
    }

    public Group(Long gid) {
        super();
        this.gid=gid;
    }

    public Group(Long gid, Map<String, String> attributes) {
        super(attributes);
        this.gid=gid;
    }

    public Group(Long gid, Map<String, String> attributes, Member member) {
        super(attributes, member);
        this.gid=gid;
    }

    public Group(Long gid, Map<String, String> attributes, Map<String, Member> members) {
        super(attributes, members);
        this.gid=gid;
    }

    public Long getGid() {
        return this.gid;
    }

    public void setGid(Long gid) {
        this.gid=gid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return  gid == group.gid && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid, super.hashCode());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Group{");
        sb.append(", gid='").append(gid.toString());
        sb.append(super.toString());
        return sb.toString();
    }
}
