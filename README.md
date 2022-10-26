# Statute-Searching : 법령 검색 서비스
⚠ 프로젝트 기간 도중,  주제 변경 이슈로 중단한 Repository입니다.

<br>

### 초기 기획 

목표 : 대한민의 법령 정보를 DB에 저장하여, 여러 법령/판례 등을 빠르고 정확하게, 상세하게 검색할 수 있는 사이트

<br>

### 발생한 문제점 및 고민

1. 대용량 데이터 처리를 실험해보기 위한 데이터 양의 부족 (각 테이블 별로 50만 건 미만의 데이터)
2. 복잡한 형태의 테이블 구조 → join 쿼리로 인한 검색 속도 저하 불가피함 : 프로젝트 취지와 맞지 않음
3. 오타 및 예외가 많고 복잡한 데이터 구조 : 데이터 정제 및 가공, 출력에 시간을 많이 소모함
    
    <div>
      <center><img src="https://user-images.githubusercontent.com/100582309/198084349-ee9230e1-7c8e-4570-a9b0-0402f48a0f95.png" width="450"></center>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<center><img src="https://user-images.githubusercontent.com/100582309/198084486-7a83b5eb-89c8-4c2e-b0ac-0cf7205efbc8.png" width="450"></center>
      <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▲ 복잡한 구조의 법령 Database ERD&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▲ 여러 join이 중첩된 쿼리문</div>
    </div>

<br><br>

### 중단 이유

: 스프링 실습팀의 목표인 '검색 시스템을 활용한 대용량 데이터 처리'에 적합하지 않다는 판단을 내림

- 지금까지 가공한 법령 데이터와 '대용량 트래픽 다루기'는 방향이 다름을 깨달음
- 데이터 분석가들의 성지라는 Kaggle에서 사용하기 좋은 **대용량(천만 건 이상)의 'instagram data set'을 발견**
- 앞으로의 남은 4주간, 현재의 법령 데이터보다는 새로운 instagram 데이터로 할 수 있을 것이 많다고 판단하여 **주제를 변경**하기로 결정함

<div>
      <center><img src="https://user-images.githubusercontent.com/100582309/198087343-50cab599-3e22-450f-a54c-9e35533f7a56.png" height="300"></center>
      <center><img src="https://user-images.githubusercontent.com/100582309/198087787-501fe145-6794-4384-b761-7fbbdd4e4995.png" height="300"></center>
      <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▲ 새로 발견한 Instagram Data Set</div>
    </div>

<br><br>

### 👉🏻 Repository 변경 링크 안내
주제 및 기획 변경에 따른 새 프로젝트는 다음 레포지토리에서 확인하실 수 있습니다.
⇒ [🔍 검색의 시작, ChadDa](https://github.com/searching-project/instagram-searching)
