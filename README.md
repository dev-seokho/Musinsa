# 프로젝트 목표
고객에게 최적의 탐색을 제안하는 메뉴 서비스를 구현합니다.

# 사용 기술 및 환경
- Java 17
- Spring Boot 3.1.1
- Spring Data JPA
- Gradle
- MySQL

# 사용 방법 및 주의 사항


# 프로젝트 중점사항
- Jacoco를 활용해서 Controller, Service, Repository Layer 테스트 커버리지를 70프로 이상 유지합니다.
- 도메인 디렉토리 구조를 프로젝트에 적용합니다.
- `@RestControllerAdvice`와 `@ExceptionHandler`를 활용하여 예외를 관리합니다.
- Google Code Style을 준수합니다.
    - Indent Size는 4를 적용합니다.
    - Tab Width는 4를 적용합니다.
- Git Commit Message Convention을 활용합니다.
    - `<타입>: <제목>` 과 같은 방식으로 작성합니다.

| 타입 이름    | 설명                            | 
|----------|-------------------------------|
| Init     | 세팅 관련 커밋                      |
| Feat     | 새로운 기능을 추가할 경우                |
| Fix      | 버그를 고친 경우                     |
| Style    | 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우 |
| Refactor | 코드 리팩토링                       |
| Test     | 테스트 추가, 테스트 리팩토링 (프로덕션 코드 변경 X) |
| Rename   | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우  |
| Remove   | 파일을 삭제하는 작업만 수행한 경우           |

# Use Case

- **[메뉴 등록]**
  - [POST] `/menus`
  - 메뉴를 등록할 수 있습니다.
  - 메뉴를 등록하기 위해서 메뉴 타이틀과 메뉴 링크를 입력해야 합니다.
  - 메뉴 타이틀과 링크는 공백일 수 없으며, 링크는 형식에 알맞게 작성해야 합니다.

- **[메뉴 조회]**
  - [GET] `/menus?page={page}&size={size}&sort={sort}`
  - 메뉴들을 Slice 형태로 조회할 수 있습니다.
  - 페이징 기능이 제공됩니다.
    - 기본 값은 30개 입니다.
  - 정렬 기능이 제공됩니다.
    - 기본값은 최신 순[cratedAt] 입니다.

- **[단일 메뉴 조회]**
  - [GET] `/menus/{menuId}`
  - 단일 메뉴를 조회할 수 있습니다.
  - 해당 메뉴의 하위 메뉴를 조회할 수 있습니다.

- **[메뉴 수정]**
  - [PATCH] `/menus/{menuId}`
  - 메뉴를 수정할 수 있습니다.
  - 메뉴를 수정하기 위해서 메뉴 타이틀과 메뉴 링크를 입력해야 합니다.
  - 메뉴 타이틀과 링크는 공백일 수 없으며, 링크는 형식에 알맞게 작성해야 합니다.

- **[메뉴 삭제]**
  - [DELETE] `/menus/{menuId}`
  - 메뉴를 삭제할 수 있습니다.

- **[배너 등록]**
  - [PATCH] `/menus/{menuId}/banners`
  - 메뉴에 배너를 등록하여 추가 노출 할 수 있습니다.
  - 배너는 공백일 수 없습니다.

- **[서브 메뉴 등록]**
  - [POST] `/menus/{menuId}/sub-menus`
  - 메뉴 하위의 서브 메뉴를 등록할 수 있습니다.
  - 서브 메뉴를 등록하기 위해서 서브 메뉴 타이틀을 입력해야 합니다.
  - 서브 메뉴 타이틀은 공백일 수 없습니다.

# 데이터베이스 ERD
