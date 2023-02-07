## 프로젝트 규칙

+ 대상 : 프로젝트를 처음 살펴볼 개발자
+ 목적 : 프로젝트의 전체적인 코드 작성 규칙 파악을 쉽게 하기 위해 작성하였다.
+ 업데이트 : 2023.02.05

### 공통

+ 상수 / 멤버 / 생성자 / 메서드 로 구분하며 각 구분간은 2줄, 구분내는 1줄 뛰어쓰기를 한다.
+ 상수는 항상 최상단에 위치시킨다.
+ 클래스의 {} 는 위아래는 1줄 뛰어쓴다. 

+ `@NoArgsConstructor`를 제외한 생성자는 명시적으로 구현한다.

+ `this`는 꼭 필요한 경우가 아니라면 생성자에서만 사용한다.

+ 계층간 이동이 필요한 경우 mapper을 사용하여 반환한다.

+ 계층간 이동에는 DTO를 활용한다.

+ 메서드 체이닝이 2개가 넘어가면 줄을 넘겨 작성한다.
  ```java
  // Example
  Long memberId = userService.getUserByToken(accessToken)
    						.getId();
  ```

+ 파라미터가 2개가 넘어가면 줄은 넘겨 작성한다.

+ 메서드의 반환값이 없는 경우 java docs는 method, param, return 모두 작성한다.
  ```java
    // Example
    /**
     * OO을 기록하는 기능입니다.
     * @param memberId member의 id
     */
  ```

+ 메서드의 반환값이 있다면 java docs는 param, return만 작성한다.
  ```java
    // Example
    /**
     * @param memberId member의 id
     * @param localDate 날짜
     * @return 조회 날짜의 member의 기록
     */
  ```

+ java docs는 상황을 기준으로 작성하지 않는다. 값을 기준으로 작성한다.
  ```java
    // Example
    /**
     * @param memberId 조회할 member의 id -> member의 id
     */
  ```

---

### 도메인

+ Getter는 사용하지만 Setter는 사용하지 않는다.

Getter는 도메인이 가지고 있는 멤버에 한해서만 사용한다.

도메인이 직접 가지고 있는 정보가 아닌 정보를 조회하려면 `getValueOfXX` 와 같이 조회한다.

+ 도메인이 가지고 있는 멤버가 조작, 계산이 필요한 경우는 Getter가 아닌 그 행위를 잘 나타낼 수 있는 메서드 명을 통해 조회 기능을 구현한다.


+ 연관관계가 있는 도메인의 경우 기본 생성자가 아닌 정적 팩터리 메서드를 통해 도메인을 생성한다.

```java
// Example
// User와 UserInfo는 연관관계를 가진다.
@Getter
public class User {
  	...
    private String id;

    private UserInfoVO userInfoVO;
		...
      
    private User(UserInfoVO userInfoVO) {
        this.userInfoVO = userInfoVO;
        this.userId = userInfoVO.getId();
    }

    public static User create(UserInfoVO userInfoVO) {
        return new User(userInfoVO);
    }
    
}

```



---

### 서비스

+ 서비스에 해당하는 도메인에 관한 Repository, Mapper와 같은 것은 도메인명을 제외하고 사용한다.

```java
public class OOOServiceImpl implements OOOService {

    private final OOORepository repository;
    private final OOOMapper mapper;
		...
}
```



+ Transaction의 기본은 `readOnly`로 설정하고 메서드별로 필요시에 `@Transaction`을 추가하여 사용한다.



+ 반환값을 넘기는 값이 도메인 값인 경우 mapper을 통해 dto로 변화시킨 후 반환한다.


+ 서비스에서 다른 도메인의 비지니스 로직이 필요하다면 그 값을 계산 값을 파라미터로 받는다.
    + 필요시 facadePattern을 사용한다.


+ 서비스에서 fk 때문에 다른 도메인을 사용할 때는 validateService에서 조회한 것을 사용한다.


---

### 컨트롤러

+ 컨트롤러에서 사용하는 dto의 경우 request, response로 명명한다.
