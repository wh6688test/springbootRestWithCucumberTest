package org.tutorials.wproject2.test.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;



public class GroupIn {

    protected Map<String, String> attributes;
    protected Map<String, Member>members;

    //private static AtomicLong sequence = new AtomicLong(0);

    protected void makeGroupIn() {
        this.attributes=new HashMap<>();
        this.members=new HashMap<>();
    }

    protected void makeMember(Member member) {
        System.out.println("before add member : "+member.toString());
        if (member.getId() != null) {
            System.out.println("add member : ");
            System.out.println("add member : "+member.toString());
            this.members.put(member.getId(), member);
        }
    }

    protected  void makeMembers(Map<String, Member>members) {
        members.forEach((k, v) -> {
            if (k != null && v.getId() == k) {
                this.members.put(k, v);
            }
        });
    }

    protected void makeAttributes(Map<String, String> attributes) {
        if (attributes == null) return;
        attributes.forEach((k, v) -> {
            if (k != null) {
                this.attributes.put(k, v);
            }
        });
    }
    public GroupIn() {
        makeGroupIn();
    }

    public GroupIn(Map<String, String> attributes) {
        makeGroupIn();
        makeAttributes(attributes);
    }

    public GroupIn(Map<String, String> attributes, Member member) {
        if (member == null) return;
        makeGroupIn();
        makeAttributes(attributes);
        makeMember(member);

    }

    public GroupIn(Map<String, String> attributes, Map<String, Member> members) {
        if (members == null) return;
        makeGroupIn();
        makeAttributes(attributes);
        makeMembers(members);
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        if (this.attributes == null) {
            this.attributes=new HashMap<>();
        }
        makeAttributes(attributes);
    }

    public Map<String, Member> getMembers() {
        return this.members;
    }

    public void setMembers(Map<String, Member>members)  {
        if (this.members == null) {
            this.members=new HashMap<>();
        }
        makeMembers(members);
    }

    public void addMember(Member member)  {
        if (this.members == null) {
            this.members=new HashMap<>();
        }
        makeMember(member);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupIn group = (GroupIn) o;
        return
                Objects.equals(attributes, group.attributes) && attributes.size() == attributes.size() &&
                Objects.equals(members, group.members) && members.size() == group.members.size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributes, members);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Group{");
        sb.append(", attributes='").append(attributes.toString());
        sb.append(", members='").append(members.toString());
        sb.append('}');
        return sb.toString();
    }

}
