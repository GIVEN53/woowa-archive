# 입력
- [x] 월과 시작 요일을 입력받는다.
  - 월: 숫자(1 ~ 12), 요일: 문자열(일, 월, 화, 수, 목, 금, 토)
- [x] 평일 비상 근무 순서를 입력받는다.
- [x] 휴일 비상 근무 순서를 입력받는다.
- [x] 올바르지 않은 입력일 경우, 다시 입력받는다.
- [x] 평일 순번 또는 휴일 순번 값이 올바르지 않을 경우 평일 순번부터 다시 입력받는다.

### 예외
- [x] 월이 1 ~ 12 사이가 아닐 경우
- [x] 요일이 일, 월, 화, 수, 목, 금, 토가 아닐 경우
- [x] 월과 요일, 평일 순번, 휴일 순번 입력이 ","로 구분되지 않을 경우
- [x] 각 순번 중 중복된 닉네임이 있을 경우

# 출력
- [x] 월별 비상근무표를 출력한다.
  - 월별 비상근무표는 1일부터 마지막 날짜까지 출력
  - 평일이면서 법정공휴일이면 요일 뒤 "(휴일)" 표기
  - x월 x일 x요일 닉네임
- [x] 에러를 출력한다.

# 사원
- [x] 닉네임 길이가 1 ~ 5자 사이인지 확인한다.
- [x] 닉네임이 중복되는지 확인한다.
- [x] 사원이 최소 5명 이상인지 확인한다.
- [x] 사원이 최대 35명 이하인지 확인한다.
- [ ] 평일 순번과 휴일 순번의 개수가 같은지 확인한다.

# 근무 배정
- [x] 평일 순번대로 근무자를 배정한다.
- [x] 공휴일인지 확인한다.
- [x] 공휴일이면 휴일 순번대로 근무자를 배정한다.
- [x] 휴일 순번대로 근무자를 배정한다.
- [x] 연속 2일 근무자가 있는지 확인한다.

# 월
- [x] 월이 1 ~ 12 사이인지 확인한다.
- [x] 해당 월의 공휴일을 확인한다.

# 요일
- [x] 요일이 일, 월, 화, 수, 목, 금, 토인지 확인한다.
- [x] 해당 요일의 평일인지 확인한다.
- [x] 해당 요일의 주말인지 확인한다.
- [x] 기준 요일을 첫 번째 요일로 설정한다.