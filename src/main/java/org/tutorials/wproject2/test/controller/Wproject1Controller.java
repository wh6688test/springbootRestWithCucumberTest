package org.tutorials.wproject2.test.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.tutorials.wproject2.test.model.Group;

import org.tutorials.wproject2.test.model.GroupIn;
import org.tutorials.wproject2.test.model.Member;
import org.tutorials.wproject2.test.service.IGroupService;

import java.util.*;


@RestController
@RequestMapping(value="/wrest")
public class Wproject1Controller {

    @Autowired
    private IGroupService groupService;

    @Autowired
    public void setGroupService(IGroupService groupService) {
        this.groupService = groupService;
    }

    @Autowired
    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    //returns empty list if no group exists
    @ApiResponses(value= {
            @ApiResponse ( responseCode="200", description="Found all the groups",
                    content={@Content(mediaType="application.json")}
            )
    })
    @GetMapping(value="/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> getGroups() {
            return groupService.findAll();
    }

    //throws not found exception for non-existing gid
    @ApiResponses(value= {
            @ApiResponse ( responseCode="200", description="Found the group by id",
                    content= {@Content(mediaType = "application.json", schema = @Schema(implementation = Group.class))
            }),

            @ApiResponse ( responseCode="400", description="invalid id supplied", content=@Content),

            @ApiResponse ( responseCode="404", description="Group not found", content=@Content),

    })
    @Operation(summary="Get the group by gid")
    @GetMapping(value="/group")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Group> getGroup(@RequestParam(value="gid") Long gid) {

        return groupService.findGroup(gid);
        
    }

    //returns null instead of NotFoundException
    @GetMapping(value="/group/attr")
    @ResponseStatus(HttpStatus.OK)
    public Group getGroupAttr(@RequestParam Long gid) {
        return groupService.findGroupAttr(gid);

    }

    //empty list if no member exists
    @GetMapping(value="/group/member/{memberId}", produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    public Optional<List<Group>> getGroupWithMemberId(@PathVariable String memberId) {

        return groupService.findMemberById(memberId);
        

    }

    @PostMapping(path="/group", consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Group createGroup(@RequestBody GroupIn groupIn) {

            return groupService.createGroup(groupIn);

    }

    //do nothing if the group does not exist
    @DeleteMapping(path="/group/{gid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable Long gid) {
       
        Optional<Group>returnedGroup=groupService.findGroup(gid);
        if (returnedGroup.isPresent()) {
            groupService.deleteGroup(returnedGroup.get());
        }
    }

    //do nothing if group does not exist
    @PutMapping(value="/group/{gid}/attr")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Group> updateGroupAttributes(@PathVariable Long gid, @RequestBody Map<String, String> attributeIn) {
        return groupService.updateGroupAttribute(gid, attributeIn);
        
    }
    //do nothing if group does not exist
    @PutMapping(value="/group/member/rating")
    @ResponseStatus(HttpStatus.OK)

    public Optional<Group> updateMemberRating(@RequestParam Long gid, @RequestBody Member member) {

        return groupService.updateGroupMember(gid, member);
       
    }

}