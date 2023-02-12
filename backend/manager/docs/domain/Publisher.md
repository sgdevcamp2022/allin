## PUBLISHER

PUBLISHER는 방송 송출자의 방송 송출과 관련된 서비스를 돕습니다.

외부적으로 방송 송출자 관련 기능(PublisherService)은 다음과 같습니다.
+ 방송 송출자 등록
+ 방송 키 조회
+ 방송 키 수정
+ 방송 비밀번호 생성
+ 방송 비밀번호 초기화
+ 방송 비밀번호 사용
+ 방송 주소 조회

그리고 내부적으로 방송 송출자와 연관이 되어있는 도메인(Room, BroadCast)에 방송 송출자에 관한 검증을 해주는 기능(PublisherValidateService)이 있습니다.

### 구현 소개

#### PUBLISHERPASSWORD
````java
public static PublisherPassword relatedFrom(Long publisherId) {
    return new PublisherPassword(publisherId);
}
````

PublisherPassword는 Publisher와 연관관계를 가지고 있습니다.

그렇기에 연관관계에 대한 것을 조금 더 잘 나타내기 위해 `relatedFrom`과 같은 정적 메서드를 통해 연관관계가 있다는 것을 나타내어 주었습니다.

#### 방송 송출자 등록
```java
@Transactional
public void save(long memberId) {
    Optional<Publisher> byMemberId = repository.findByMemberId(memberId);
    if (byMemberId.isPresent()) {
        throw new PublisherServiceValidateException(PublisherServiceException.EXIST_PUBLISHER);
    }

    String key = md5.encode(UUID.randomUUID().toString());

    repository.save(new Publisher(memberId, key));
}
```

방송 송출 등록은 하나의 MemberId는 하나의 PublisherId를 가질 수 있습니다.

그렇기에 동일한 MemberId가 발견되면 `EXIST_PUBLISHER` 예외를 발생시킵니다.

그리고 UUID를 통해 생성한 랜덤한 수를 MD5를 통해 인코딩하여 키를 생성합니다.

이 키와 함께 방송 송출자를 생성합니다.

#### 방송 비밀번호 사용
```java
@Transactional
public void usePassword(String password) {
    PublisherPassword publisherPassword = publisherPasswordRepository.findByValue(password)
        .orElseThrow(
            () -> new PublisherServiceValidateException(
                PublisherServiceException.NO_MATCH_PASSWORD));

    publisherPassword.use();
}
```

```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "publisher_password_table")
@Where(clause = "publisher_password_used = false")
public class PublisherPassword {
    ...

    public void use() {
        used = true;
    }
}
```

방송 비밀번호는 일회성으로 방송 송출이 되면 사용됩니다.

사용되면 기존의 `true`였던 값이 `false`로 변화면서 `@Where(clause = "publisher_password_used = false")`의 조건에 의해 삭제된 기록으로 간주됩니다.

