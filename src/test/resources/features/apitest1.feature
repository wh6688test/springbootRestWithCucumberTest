# Created by jenkins at 2020-10-12
Feature: any test
  any purpose
  Scenario: scenario 1
    # Enter steps here
    Given api request uri '/group' and expected status code 201
    When I create the group using the following attributes successfully:
    |attr1  |attr2 |
    |value1 |value2|
    Then I retrieve specific group via api '/group' using 'gid' 1 with expected response status code 200
    Then I verify that the response contents 'do' contain following values:
    |gid|attr1|attr2|
    |1 |value1|value2|


   Scenario: scenario 2
    # Enter steps here
   Given the group with gid 1 exists or get created
   And api request uri '/group/member/rating' and expected status code 200
   When I update the group member:
     |gid|memberId|rating|
     |2  |3       |5     |
   Then I retrieve the group list with group member id '1' via api '/group/member' with expected response status 200
   And I verify that the response contents 'do' contain following values:
    |gid|memberId  |rating|
    |2  |3 |5|
   Then I delete the group with gid 1 via api "/group" with response status 204
   And I retrieve all groups via api "/groups" with response status 200
   Then I verify that the response contents 'do not' contain following values:
    |gid|attr1|attr2|
    |2  |value1|value2|