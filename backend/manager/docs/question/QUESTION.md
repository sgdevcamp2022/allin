## QUESTION

### Mapper

```java
@Component
public class BroadCastMapper {

    public OnLiveBroadCastListDTO of(List<BroadCast> liveBroadCastList) {
        List<BroadCastDTO> broadCastDTOList = liveBroadCastList
            .stream()
            .map(BroadCastDTO::new)
            .collect(Collectors.toList());

        return new OnLiveBroadCastListDTO(broadCastDTOList);
    }

}

```

```java
// BroadCastService

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BroadCastService {

    private final BroadCastRepository repository;

    private final BroadCastMapper mapper;
    
    ...

    public OnLiveBroadCastListDTO liveList() {
        List<BroadCast> onLiveBroadCastList = repository.findAllByState(BroadCastState.LIVE);

        return mapper.of(onLiveBroadCastList);
    }
}
```

위의 코드는 BroadCast가 아닌 DTO로 값을 반환해주기 위한 Mapper와 BroadCastService에서 그것을 사용하는 예제입니다.

한 계층에서의 변경사항이 다른 계층에 영향을 미치지 않아야 한다고 알고 있습니다.

그래서 저는 이번 프로젝트에서는 위에서 확인할 수 있는 것처럼 Mapper을 통해 계층간 이동에는 DTO로 값을 변환시켜 이동시키고 있습니다.

우선 이렇게 **계층간 이동에서 Mapper을 사용해서 DTO로 변환 시켜주는 것**이 옳은 판단인지 궁금합니다.

그리고 맞다면 **Mapper는 컴포넌트로 선언하여 사용하는 것이 좋은지 static으로 선언해서 사용해서 사용하는 것이 좋은지** 궁금합니다.
 
이는 컴포넌트와 static에 관해 [정리된 글](http://kwon37xi.egloos.com/4844149?fbclid=IwAR0YAxeekXxjB7clzFLpCfqQ90FiwSPTyrN1SH3rG_JFrZ0lvJz_pv_eudI)을 통해 컴포넌트와 static의 사용 사례를 알아보기는 하였지만 여전히 확실한 기준이 서지는 않아 질문합니다.

---

### 도메인간 연관관계

![MANAGER_ERD](https://user-images.githubusercontent.com/102807742/218324182-6e7a35bb-8234-4b44-bc4b-0088c5539521.png)

위는 Manager 서버의 ERD입니다.

많은 도메인이 Publisher와 연관관계를 가지고 있습니다.

그렇기에 아래와 같이 Publisher를 다른 도메인을 사용할 때도 조회해야 하는 경우가 있습니다.

```java
// RoomController

@PostMapping
public ApiResponse<ApiResponse.withCodeAndMessage> makeRoom(Long memberId, RoomInfoRequest roomInfoRequest) {
    Long validatePublisherId = publisherValidateService.validatePublisher(memberId, PublisherValidateIdType.MEMBER);
    roomService.save(validatePublisherId, roomInfoRequest);

    return ApiResponseGenerator.success(
        HttpStatus.OK,
        HttpStatus.OK.value() + ManagerServerCode,
        "success make room"
    );
}
```

저는 이런 경우에 publisherId를 조회하기 위해 PublisherValidateService라는 publisherId 조회를 위한 서비스를 따로 만들어 주었습니다.

이렇게 PublisherValidateService라는 publisherId 조회를 위한 **서비스를 따로 만든 것**이 옳은 판단인지 궁금합니다.

그리고 보통 실무에서는 **1:N 연관관계를 어떻게 처리하는지** 궁금합니다.

+ 양방향 연관관계를 많이 사용하나요?
+ 단방향 연관관계를 사용할 때 N의 1에 대한 정보는 어떻게 넣나요?
  + JPA를 사용하면 1의 객체를 넣는데 저는 이번 프로젝트에서는 연관관계를 줄이고 싶어서 1의 id만 Long으로 넣어주었습니다.
    ```java
    public class Room {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "room_id")
        private Long id;

        @Column(name = "publisher_id")
        private Long publisherId; // 기존에는 Publisher publisher와 같이 사용하였음
    
        ...
    }
    ```

---

### DB 테이블 설계

위의 ERD를 보시면 PUBLISHER_TABLE 그리고 PUBLISHER_PASSWORD_TABLE

ROOM_TABLE 그리고 ROOM_INFO_TABLE과 같이 정보를 분리하였습니다.

저는 한 테이블 설계 [정리 글](https://rastalion.me/%ED%9A%8C%EC%9B%90-%EA%B0%80%EC%9E%85-%EB%B0%8F-%EB%A1%9C%EA%B7%B8%EC%9D%B8%EC%9D%84-%EC%9C%84%ED%95%9C-%ED%85%8C%EC%9D%B4%EB%B8%94-%EC%84%A4%EA%B3%84/)을 보고 정보 속성에 따라 테이블을 분리할 필요가 있다는 것을 알았습니다.

그래서 이번 프로젝트에서는 수정 빈도에 따라 테이블을 분리하여 보았습니다.

위처럼 **수정 빈도에 따라 테이블을 분리한 것이 좋은 판단인지** 궁금하고 **테이블을 설계할 때 중요한 사항이나 팁이 있는지** 궁금합니다.

---

### SQL

```java
@Where(clause = "DELETE_AT IS NULL") // H2
@Where(clause = "DELETE_AT != MODIFIED_AT") // MariaDB
```

알고는 있었지만 신경을 잘 쓰지 못한 부분이 SQL에 관한 부분이었습니다.

이번 프로젝트를 하면서 SQL 드라이버 마다 차이가 있다는 것을 위와 같은 경우로 다시 한번 인지하였습니다.

처음 H2를 사용할 때는 DELETE_AT에 <null>이 들어갔지만 배포를 하고 MariaDB를 사용하니 DELETE_AT에 Null이 들어가지 않고 <default> 값이 들어가는 차이가 있었습니다.

H2를 개발할 때 많이 사용하는데 그렇게되면 MariaDB와 문법이 차이가 있을 수 있는데 이러한 **차이를 어떻게 감안하고 개발을 진행할 수 있을지** 궁금합니다.