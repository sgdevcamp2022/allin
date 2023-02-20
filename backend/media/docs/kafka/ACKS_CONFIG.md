## ACKS CONFIG

ACKS는 프로듀서가 전송한 데이터가 브로커들에 정상적으로 전송되었는지 전송 성공 여부를 확인하는 데에 사용하는 옵션입니다.

0, 1, -1 중 하나로 값을 설정할 수 있는데 이 값에 따라 데이터의 유실 가능성이 달라집니다.

+ 0 : 프로듀서가 **전송한 즉시 브로커에 데이터 저장 여부와 상관 없이 성공**으로 판단한다.
+ 1 : 리더 파티션에 데이터가 저장되면 전송 성공으로 판단한다.
+ -1 : 리더 파티션과 팔로워 파티션에 데이터가 저장되 성공한는 것으로 판단한다.

해당 프로젝트에서는 **ACKS 설정을 0**으로 설정하였습니다.

카프카는 데이터 전송이 실패했을 때 재시도를 할 수 있도록 retries 옵션을 설정할 수 있는데, 

ACKS가 0일 때는 프로듀서는 전송을 하자마자 데이터가 저장되었음을 가정하고 다음 데이터를 전송하기 때문에 **데이터 전송이 실패한 경우를 알 수 없습니다.**

**그렇기에 retries 옵션이 무의미해집니다.**

하지만 해당 프로젝트는 라이브 방송을 전달한다는 점에서 전송 실패에 대한 대처보다 **실시간성과 빠른 전송이 더 중요하다고 판단**하여 ACKS 설정을 0으로 하게 되었습니다.