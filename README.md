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
- Jacoco를 활용해서 테스트 커버리지를 70프로 이상 유지합니다.
- 도메인 디렉토리 구조를 프로젝트에 적용합니다.
- `@ControllerAdvice`와 `@ExceptionHandler`를 활용하여 예외를 관리합니다.
- Google Code Style을 준수합니다.
    - Indent Size는 4를 적용합니다.
    - Tab Width는 4를 적용합니다.
- Git Commit Message Convention을 활용합니다.
    - `<타입>: <제목>` 과 같은 방식으로 작성합니다.

| 타입 이름    | 설명                               | 
|----------|----------------------------------|
| Init     | 초기 세팅 관련 커밋                      |
| Feat     | 새로운 기능을 추가할 경우                   |
| Fix      | 버그를 고친 경우                        |
| Style    | 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우 |
| Refactor | 코드 리팩토링                          |
| Test     | 테스트 추가, 테스트 리팩토링 (프로덕션 코드 변경 X)  |
| Rename   | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우     |
| Remove   | 파일을 삭제하는 작업만 수행한 경우              |

# Use Case

# 데이터베이스 ERD
