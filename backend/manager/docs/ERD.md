## ERD

![MANAGER_ERD](https://user-images.githubusercontent.com/102807742/218324182-6e7a35bb-8234-4b44-bc4b-0088c5539521.png)

### Publisher

PUBLISHER는 서비스에 가입된 회원 중 PUBLISHER인 회원을 관리하기 위한 테이블입니다.

`PUBLISHER_KEY`는 방송 송출자가 방송을 하기 위한 키입니다.

`PUBLISHER_PASSWORD_TABLE`은 PUBLISHER의 비밀번호를 별도의 테이블로 분리한 것입니다.

분리한 이유는 `PUBLISHE_TABLE`에 있는 정보와 달리 `PUBLISHER_PASSWORD_TABLE`의 정보들을 방송 때마다 발급하기에 변경이 자주 일어납니다.

**정보의 변경 빈도 차이가 존재**하기에 별도의 테이블로 분리하는 것이 더 효율적인 설계라고 판단하여 분리하였습니다.

---

### ROOM

ROOM은 방송 송출자가 자신의 방송에 관하여 소개할 수 있도록 하나씩 테이블입니다.

그렇기에 `PUBLISHER_ID`를 FK로 가지고 PUBLISHER와 1:1로 연관되어 있습니다.

`ROOM_TABLE`의 경우 `ROOM_INFO_TABLE`로 `ROOM_TABLE`에 관한 정보를 분리하였기 때문에 수정시간(`MODIFIED_AT`)은 존재하지 않습니다. 

`ROOM_INFO_TABLE`은 위의 `PUBLISHER_PASSWORD_TABLE`와 동일한 이유로 분리하였습니다.

`PUBLISHER_PASSWORD_TABLE`의 경우 최초 생성시간은 `ROOM_INFO_TABLE`과 동일하기에 존재하지 않고 수정시간과 삭제시간만 존재합니다.

---

### BROAD_CAST

BOARD_CAST는 방송 송출자의 방송과 관련된 기록을 저장하기 위한 테이블입니다.

방송 송출 상태를 다른 서비스에게도 제공해야하기에 이를 `BROAD_CAST_STATE` 칼럼을 통해서 표시합니다.

방송 중 일때는 `BROAD_CAST_STATE`를 `START`로 방송 종료 시에는 `END` 상태를 변경할 수 있습니다.

그렇기에 `CREATE_AT`은 방송 송출이 시작된 시각, `MODIFIED_AT`은 방송 송출이 종료된 시각으로 생각할 수 있습니다.