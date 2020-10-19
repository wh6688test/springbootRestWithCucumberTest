package org.tutorials.wproject2.test.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.tutorials.wproject2.test.model.Group;
import org.tutorials.wproject2.test.model.GroupIn;
import org.tutorials.wproject2.test.model.Member;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GroupService implements IGroupService {

    private static AtomicLong sequence = new AtomicLong(1);
    private static List<Group> groups=new ArrayList<>();

    //@Autowired
    //private GroupRepository repository;

    @Override
    public List<Group> findAll() {

      return groups;
    }

    @Override
    public Optional<Group> findGroup(Long gid) {

        return groups.stream().filter(g->Objects.equals(g.getGid(), gid)).findAny();
    }

    @Override
    public Group findGroupAttr(Long gid) {

       return groups.stream().filter(g->Objects.equals(g.getGid(), gid)).findAny().orElse(null);

    }

    @Override
    public Optional<List<Group>> findMemberById(String memberId) {
        List<Group>gs=groups.stream().filter(g->g.getMembers().containsKey(memberId)).collect(Collectors.toList());
        return Optional.ofNullable(gs);
    }

    @Override
    public Group createGroup(GroupIn groupIn) {
        Long gid1=sequence.getAndIncrement();
        Group group1= new Group(gid1, groupIn.getAttributes(), groupIn.getMembers());
        groups.add(group1);
        return group1;
    }

    @Override
    public void deleteGroup(Group group)
    {
        //to add more logic later
        groups.remove(group);
    }

    @Override
    public List<Group> deleteMember(String memberId) {
        //to add more logic later
        for (Group g1 : groups) {
            //if (g1.getMembers().keySet().contains(memberId)) {
                groups.remove(g1);
                //g1.getMembers().keySet().remove(memberId);
                //groups.add(g1);
            //}
        }
        return groups;
       //existingGroup.getMembers().remove(memberId);
    }

    @Override
    public Optional<Group> updateGroupAttribute(Long gid, Map<String, String>attrs) {

        Optional<Group>foundGroup=this.findGroup(gid);

        foundGroup.ifPresent(g->{
            g.setAttributes(attrs);

        });
        return foundGroup;

    }

    @Override
    public Optional<Group> updateGroupMember(Long gid, Member memberIn) {
        Optional<Group>foundGroup=this.findGroup(gid);
        if (foundGroup.isPresent()) {
            Group updates=foundGroup.get();
            int index=groups.indexOf(updates);
            updates.addMember(memberIn);
            if (index==1) {
                groups.add(updates);
            } else {
                groups.set(index, updates);
            }
            return foundGroup;
        }
        /**
        foundGroup.ifPresent(g->{
            int index=groups.indexOf(g);
            g.addMember(memberIn);
            groups.set(index, g);
        })**/
        return Optional.of(new Group(5L));
    }

}
