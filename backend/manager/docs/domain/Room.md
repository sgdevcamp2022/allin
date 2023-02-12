## ROOM

ROOM은 방송 송출자의 방송방과 관련된 서비스를 돕습니다.

방송방 관련 기능(RoomService)는 다음과 같습니다.
+ 방송방 생성
+ 방송방 정보 조회
+ 방송방 정보 수정

### 구현 소개

#### BROADCAST
````java
public static Room relatedFrom(Long publisherId) {
    return new Room(publisherId);
}

public static RoomInfo relatedOf(Long roomId, String title, String description, ScheduleVO scheduleVO) {
    return new RoomInfo(
    roomId,
    title,
    description,
    scheduleVO
    );
}
````

Room은 Publisher와 연관관계를 가지고 있습니다.

그렇기에 연관관계에 대한 것을 조금 더 잘 나타내기 위해 `relatedFrom`과 같은 정적 메서드를 통해 연관관계가 있다는 것을 나타내어 주었습니다.

또 RoomInfo는 Room과 연관관계를 가지고 있습니다.

그렇기에 이 역시 연관관계에 대한 것을 조금 더 잘 나타내기 위해 `relatedOf`과 같은 정적 메서드를 통해 연관관계가 있다는 것을 나타내어 주었습니다.