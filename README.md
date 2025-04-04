# ScheduleProject
내일배움캠프 [Spring 6기] CH 3 일정 관리 앱 만들기

# 내일배움캠프의 세번째 과제

## 개발 기간🕰️
* 25.03.19 ~ 25.03.26



## 필수기능
### Lv0
  API 명세 및 ERD 작성  

  [✔️]  API 명세서 작성하기
  
  | 기능             | Method | URL                          | request                                                                                                      | response                                        | 상태코드             |
|-----------------|--------|------------------------------|--------------------------------------------------------------------------------------------------------------|-------------------------------------------------|----------------------|
| 일정 등록        | POST   | /schedules                    | 요청 body: `{ "requestDto": { "schedule": "테스트", "name": "테스트", "password": "테스트" }, "userRequestDto": { "name": "테스트23", "email": "5678@5679" } }` | 등록 정보 (`ScheduleResponseDto`)               | 201: 정상 등록       |
| 전체 일정 조회    | GET    | /schedules                    | 요청 파라미터: `page`, `size`, `dateCorrection`,`userName` (페이징 처리)                                                                   | 일정 목록 (`List<ScheduleResponseDto>`)         | 200: 정상 조회       |
| 선택 일정 조회    | GET    | /schedules/{schedule_id}      | 요청 파라미터: `schedule_id` (일정 ID)                                                                          | 일정 정보 (`ScheduleResponseDto`)               | 200: 정상 조회       |
| 일정 수정        | PUT    | /schedules/{schedule_id}      | 요청 body: `{ "schedule": "새 일정 내용", "password": "비밀번호" }`                                              | 수정된 일정 정보 (`ScheduleResponseDto`)        | 200: 정상 수정       |
| 일정 삭제        | DELETE | /schedules/{schedule_id}      | 요청 파라미터: `schedule_id`, 요청 body: `{ "password": "비밀번호" }`                                           | 없음                                            | 204: 정상 삭제       |
| 사용자별 일정 조회| GET    | /schedules/user/{user_id}     | 요청 파라미터: `user_id` (사용자 ID)                                                                            | 일정 목록 (`List<ScheduleResponseDto>`)         | 200: 정상 조회       |


  
  [✔️]  ERD 작성하기

  ![Image](https://github.com/user-attachments/assets/4d7405c4-2a61-46d8-9aad-508c545d6143)
  
  [✔️]  SQL 작성하기
  
    

---
  
### Lv1
  일정 생성 및 조회  
  
  [✔️]  일정 생성(일정 작성하기)
  
    - `할일`, `작성자명`, `비밀번호`, `작성/수정일`을 저장
    
    - `작성/수정일`은 날짜와 시간을 모두 포함한 형태
    
    - 각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리

    
  [✔️]  전체 일정 조회(등록된 일정 불러오기)
    
    - 다음 조건을 바탕으로 등록된 일정 목록을 전부 조회
        - `수정일` (형식 : YYYY-MM-DD)
        - `작성자명`
        
    - 조건 중 한 가지만을 충족하거나, 둘 다 충족을 하지 않을 수도, 두 가지를 모두 충족할 수도 있음.


  [✔️]  선택 일정 조회(선택한 일정 정보 불러오기)
  
    - 선택한 일정 단건의 정보를 조회할 수 있다.
    
    - 일정의 고유 식별자(ID)를 사용하여 조회함.
  
---

### Lv2
  일정 수정 및 삭제
  
  [✔️]  선택한 일정 수정

    - 선택한 일정 내용 중 `할일`, `작성자명` 만 수정 가능
    
    - 서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달
    
    - `작성일` 은 변경할 수 없으며, `수정일` 은 수정 완료 시, 수정한 시점으로 변경합니다.
    
  [✔️]  선택한 일정 삭제
  
    -  선택한 일정을 삭제할 수 있습니다.
        - 서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
  
  
---


  
## 도전기능
### Lv3
  연관 관계 설정
  
  [✔️]  작성자와 일정의 연결
  설명📝

      - 동명이인의 작성자가 있어 어떤 작성자가 등록한 ‘할 일’인지 구별할 수 없음
      
      - 작성자를 할 일과 분리해서 관리합니다.
      
      - 작성자 테이블을 생성하고 일정 테이블에 FK를 생성해 연관관계를 설정해 봅니다.
    
  [✔️]  조건
  
      - 작성자 테이블은 이름 외에 이메일, 등록일, 수정일 정보를 가지고 있습니다.
        - 작성자의 정보는 추가로 받을 수 있습니다.(조건만 만족한다면 다른 데이터 추가 가능)
      - 작성자의 고유 식별자를 통해 일정이 검색이 될 수 있도록 전체 일정 조회 코드 수정.
      - 작성자의 고유 식별자가 일정 테이블의 외래키가 될 수 있도록 합니다.
    
---

### Lv4
  페이지네이션
  
  설명📝
    
      - 많은 양의 데이터를 효율적으로 표시하기 위해 데이터를 여러 페이지로 나눕니다.
      
      - `페이지 번호`와 `페이지 크기`를 쿼리 파라미터로 전달하여 요청하는 항목을 나타냅니다.
      
      - 전달받은 페이지 번호와 크기를 기준으로 쿼리를 작성하여 필요한 데이터만을 조회하고 반환
    
  [✔️]  조건

      - 등록된 일정 목록을 `페이지 번호`와 `크기`를 기준으로 모두 조회
      - 조회한 일정 목록에는 `작성자 이름`이 포함
      - 범위를 넘어선 페이지를 요청하는 경우 빈 배열을 반환
      - Paging 객체를 활용할 수 있음

---

### Lv5
  예외 발생 처리
  
  설명📝
    
      - 예외 상황에 대한 처리를 위해 `HTTP 상태 코드`와 `에러 메시지`를 포함한 정보를 사용하여 예외를 관리할 수 있습니다.
      
        1. 필요에 따라 사용자 정의 예외 클래스를 생성하여 예외 처리를 수행할 수 있습니다.
        
        2. `@ExceptionHandler`를 활용하여 공통 예외 처리를 구현할 수도 있습니다.
        
        3. 예외가 발생할 경우 적절한 HTTP 상태 코드와 함께 사용자에게 메시지를 전달하여 상황을 관리합니다.
    
  [✔️]  조건

      - 수정, 삭제 시 요청할 때 보내는 `비밀번호`가 일치하지 않을 때 예외가 발생합니다.
      - 선택한 일정 정보를 조회할 수 없을 때 예외가 발생합니다.
        1. 잘못된 정보로 조회하려고 할 때 
        2. 이미 삭제된 정보를 조회하려고 할 때

---

### Lv6
  null 체크 및 특정 패턴에 대한 검증 수행
  
  설명📝
    
      - 유효성 검사
      
        1. 잘못된 입력이나 요청을 미리 방지할 수 있습니다.
        
        2. 데이터의 `무결성을 보장`하고 애플리케이션의 예측 가능성을 높여줍니다.
        
        3. Spring에서 제공하는 `@Valid` 어노테이션을 이용할 수 있습니다.
    
  [✔️]  조건

      - `할일`은 최대 200자 이내로 제한, 필수값 처리
      - `비밀번호`는 필수값 처리
      - 담당자의 `이메일` 정보가 형식에 맞는지 확인

---

  
  
