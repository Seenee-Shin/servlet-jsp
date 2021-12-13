//로그인 시 유효성 검사 
function loginValidate(){

  const memberId = document.getElementById("memberId")
  const memberPw = document.querySelector("#memberPw")

  //아이디가 입력되지 않은 경우 
  //"아이디를 입력해 주세요 " 출력 후 아이디로 focus 이동
  // false를 반환 하여 로그인이 되지 않게 함
  
  if(memberId.value.trim().length == 0){
    alert("아이디를 입력해 주세요")
    memberId.focus()
    return false
  } 
  
  if(memberPw.value.trim().length == 0){
    alert("비밀번호를 입력해 주세요")
    memberPw.focus()
    return false
  }

}

//메인페이지 -> 아이디로 회원정보 조회
document.getElementById("idSearchBtn").addEventListener("click",()=>{
  //입력받은 아이디를 입력받을 input 얻어옴
  const searchId = document.getElementById("searchId")

  if(searchId.value.trim().length == 0){
    alert("검색할 아이디를 입력해주세요")
  }else{
    $.ajax({
      url : "member/idSearch",
      data: {"inputId" : searchId.value},

      dataType:"json", //데이터 타입속성 지정 json형태가 jsObject형태로 변환

      success: function(member){
        const tbody = document.getElementById("idSearchResult");

        //기존 tbody내용 제거 
        tbody.innerHTML="";

        if(member==null){ //조회결과가 없을 경우
          const tr = document.createElement("tr") //tr요소 만들기 
          const td = document.createElement("td") //td요소 만들기 

          // const text = document.createTextNode(inpuId + "회원이 존재하지 않습니다.")
          td.innerText = searchId.value + "회원이 존재하지 않습니다.";

          tr.append(td)
          tbody.append(tr)
        }else{ //조회 결과가 존재할 경우
          for(key in member){
            switch(key){
              case "memberId" : 
              case "memberName" :
              case "memberPhone" :
              case "memberEmail" :
              case "memberAddress" :

              const tr = document.createElement("tr")
              const th = document.createElement("th")
              const td = document.createElement("td")
              
              
              let title 
              switch(key){
                case "memberId" : title="아이디"; break;
                case "memberName" : title="이름"; break;
                case "memberPhone" : title="전화번호"; break;
                case "memberEmail" : title="이메일"; break;
                case "memberAddress" : title="주소"; break;
              }

              th.innerText = title
              td.innerText = member[key]
              
              tr.append(th,td)
              tbody.append(tr)
            }
          }

          }
        },

        error : function(request,status,error){
          console.log("ajax통신 중 오류 발생")
          console.log(request.responseText)
        }
      })
    }
})

