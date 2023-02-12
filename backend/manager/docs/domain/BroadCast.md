## BROADCAST

BROADCAST는 방송 송출자의 방송 송출과 관련된 서비스를 제공합니다.

방송 관련 기능(BroadCastService)은 다음과 같습니다.
+ 방송 목록 조회

### 구현 소개

#### BROADCAST
````java
public static BroadCast relatedFrom(Long publisherId) {
    return new BroadCast(publisherId);
}
````

BroadCast는 Publisher와 연관관계를 가지고 있습니다.

그렇기에 연관관계에 대한 것을 조금 더 잘 나타내기 위해 `relatedFrom`과 같은 정적 메서드를 통해 연관관계가 있다는 것을 나타내어 주었습니다.