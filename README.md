![image](https://user-images.githubusercontent.com/487999/79708354-29074a80-82fa-11ea-80df-0db3962fb453.png)

# 예제 - 음식배달

본 예제는 MSA/DDD/Event Storming/EDA 를 포괄하는 분석/설계/구현/운영 전단계를 커버하도록 구성한 예제입니다.
이는 클라우드 네이티브 애플리케이션의 개발에 요구되는 체크포인트들을 통과하기 위한 예시 답안을 포함합니다.
- 체크포인트 : https://workflowy.com/s/assessment-check-po/T5YrzcMewfo4J6LW

# 서비스 시나리오

배달의 민족 커버하기 - https://1sung.tistory.com/106

기능적 요구사항
1. 고객이 메뉴를 선택하여 주문한다.
1. 고객이 선택한 메뉴에 대해 결제한다.
1. 주문이 되면 주문 내역이 입점상점주인에게 주문정보가 전달된다
1. 상점주는 주문을 수락하거나 거절할 수 있다
1. 상점주는 요리시작때와 완료 시점에 시스템에 상태를 입력한다
1. 고객은 아직 요리가 시작되지 않은 주문은 취소할 수 있다
1. 요리가 완료되면 고객의 지역 인근의 라이더들에 의해 배송건 조회가 가능하다
1. 라이더가 해당 요리를 pick 한후, pick했다고 앱을 통해 통보한다.
1. 고객이 주문상태를 중간중간 조회한다
1. 주문상태가 바뀔 때 마다 카톡으로 알림을 보낸다
1. 고객이 요리를 배달 받으면 배송확인 버튼을 탭하여, 모든 거래가 완료된다

# 추가구현 사항
- store / delivery 에 각각 ReadModel 추가
- store : 주문 접수 시 foodId 별 Count를 증가하여 주문량 확인 가능
- delivery : riderId 별 배달 접수 시 Count를 증가하여 rider별 배달 횟수 확인 가능
- 
![image](https://user-images.githubusercontent.com/117624181/203259582-715ebc3e-0bf3-4c3d-ad9b-29bb24eb3670.png)

https://labs.msaez.io/#/storming/H6tLbk0ptNX2TgnchFK15fnL8xl1/cbb21a2c294f5219828d43c9f93e5e35
![image](https://user-images.githubusercontent.com/117624181/203246967-dccd2e13-74f4-412c-98fe-5aace4166634.png)

# 체크포인트

1. Saga (Pub / Sub)
- kafka를 통해 publish / subscribe 으로 비동기 통신 구현

![image](https://user-images.githubusercontent.com/117624181/203247498-03b6cae2-5b0d-43a3-b77a-c6a559507cb1.png)
![image](https://user-images.githubusercontent.com/117624181/203247565-866eb166-751e-41e5-aef2-8012471907e4.png)


2. CQRS
- readModel 에서 지속적으로 상태 업데이트

![image](https://user-images.githubusercontent.com/117624181/203249105-4397f6a1-859c-4342-87d7-3778897cbb2f.png)
![image](https://user-images.githubusercontent.com/117624181/203249058-b58fab7c-df93-4a86-aba5-fd685e86d1b5.png)

3. Compensation / Correlation
- 주문 상태에 따라 취소/취소 불가 구현

![image](https://user-images.githubusercontent.com/117624181/203258212-9c6b9d41-59cc-4994-b0c7-cb31678439d0.png)


4. Request / Response
- 동기적으로 결제가 진행되도록 구현

![image](https://user-images.githubusercontent.com/117624181/203249339-5c6b4406-5fd7-4885-989e-b9fd270d56b3.png)

5. Circuit Breaker
- 서킷 브레이킹 프레임워크의 선택: Spring FeignClient + Hystrix 옵션을 사용하여 구현함
주문-->결제 시의 연결을 Req/Res 로 연동하여 구현하였고, 결제에 많은 시간이 소요될 경우 서킷 브레이커로 장애처리.

![image](https://user-images.githubusercontent.com/117624181/203254371-4a9bc2a4-5cd8-4414-9069-3ed1759db1d4.png)

![image](https://user-images.githubusercontent.com/117624181/203254333-71e8baaa-f0a8-409e-8e8c-d75106da613e.png)


6. Gateway 

![image](https://user-images.githubusercontent.com/117624181/203249487-c73153bd-3ac6-4f16-a206-f95e316f688b.png)


