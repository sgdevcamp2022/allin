## MANAGER

MANAGER는 방송 송출과 관련된 서비스를 제공합니다.

방송 관련 기능(BroadCastService)은 다음과 같습니다.
+ 방송 시작
+ 방송 종료

방송 관련 기능(BroadCastService)의 일부 기능이 BROADCAST 보다는 

보다 관리에 초점을 맞춘 MANAGER로 분리하는 것이 더 좋을 것이라 판단하여 분리하였습니다.

### 구현 소개

#### 방송 시작과 종료
```java
@Transactional
public void startLive(Long publisherId) {
    if (repository.existByPublisherAndStateIsLive(publisherId)) {
        throw new BroadCastServiceValidateException(BroadCastServiceException.ALREADY_ON_LIVE);
    }

    BroadCast broadCast = BroadCast.relatedFrom(publisherId);
    repository.save(broadCast);
}

@Transactional
public void endLive(Long publisherId) {
    BroadCast liveBroadCast = repository.findByPublisherAndState(publisherId, BroadCastState.LIVE)
        .orElseThrow(() -> new BroadCastServiceValidateException(BroadCastServiceException.NO_MATCH_LIVE));

    liveBroadCast.end();
}
```

방송 시작의 경우 우선 방송 송출자가 방송 송출 중인 것인 있는지(`existByPublisherAndStateIsLive(publisherId)`) 확인한 이후 방송 송출 기록합니다.

만약 방송 송출 중이라면 `ALREADY_ON_LIVE` 예외를 발생 시킵니다.

방송 송출이 종료 될 때는 아래와 같이 방송 상태를 변경합니다.

```java
public void end() {
    state = BroadCastState.end();
    sqlDateTime = sqlDateTime.modify();
}
```

이때 방송 상태를 `END`로 수정하여도 삭제하는 것이 아닌 수정만 하고 수정시간을 기록합니다.

이 수정시간이 방송 종료 시간이 될 것이고 생성시간이 방송 시작시간이 될 것 입니다.