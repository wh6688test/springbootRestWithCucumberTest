package org.tutorials.wproject2.test.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.tutorials.wproject2.test.model.Group;
import org.tutorials.wproject2.test.model.GroupIn;
import org.tutorials.wproject2.test.model.Member;

public interface IGroupService {

    List<Group> findAll();

    Optional<Group>findGroup(Long gid);

    Group findGroupAttr(Long gid);

    Optional<List<Group>> findMemberById(String memberId);


    Group createGroup(GroupIn groupIn);

    void deleteGroup(Group group);

    //void deleteMember(Group group, String memberId);
    List<Group> deleteMember(String memberId);

    Optional<Group> updateGroupAttribute(Long gid, Map<String, String> attr);

    Optional<Group> updateGroupMember(Long gid, Member memberIn);

}
