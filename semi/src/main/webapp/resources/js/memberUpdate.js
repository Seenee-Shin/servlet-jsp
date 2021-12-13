
//모든 요소에 유효한 값이 작성되어있는지 확인하는 용도의 객체
// 이미 입력되어있는 회원정보는 유효성검사를 끝낸 값이기 때문에 true상태      
const updateCheckObj ={
  phone3: true,
  email:true
}

let temp
//전화번호 글자 입력 수 제한 
$(".phone").on("input",function(e){

  if($(this).val().length > 4){      
    const num = $(this).val().slice(0,4) //인덱스 0부터 4미만으로 자름

    //잘라서 4자리만 남은 값을 현재 입력중인 input태그의 value로 대입
    $(this).val(num)
  }

  

  if(console.log(e.originalEvent.data)=="e"){
    $(this).val(temp)
  }else{
    temp=$(this).val()
  }
  
})



//전화번호 유효성 검사
$(".phone").on("change",function(){
  //각각 입력된 번호 얻어오기 
  const inputPhone2 = document.getElementById("phone2").value
  const inputPhone3 = document.getElementById("phone3").value
  
  const regex2 = /^[\d]{3,4}$/ 
  const regex3 = /^[\d]{4}$/ 
  
  
  
  if(regex2.length == 0 || regex3.length == 0){
    updateCheckObj.phone3 =false
  
  }else if(regex2.test(inputPhone2) && regex3.test(inputPhone3) ){
    updateCheckObj.phone3=true
  }else{

    alert("전화번호가 유효하지 않습니다.")
    updateCheckObj.phone3=false
    this.focus()
  
  }
  
})


//*새로운 이메일을 입력 후 다시 기존 이메일을 작성 할 때, 알림창 팝업 방지 */
//기존 이메일 저장 
const existingEmail = document.getElementById("email").value

//이메일 값이 변할때 유효성 검사 진행 
//이메일 유효성 검사
//const regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
document.getElementById("email").addEventListener("change", function(){

  const inputEmail = this.value.trim()
  const regex = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/
  

  //입력된 이메일이 기존 이메일일 경우
  if(inputEmail == existingEmail){
    updateCheckObj = true

  }else if(inputEmail.length == 0){
    checkEmail.innerText ="이메일을 입력해 주세요"
    checkEmail.style.color ="black"
    updateCheckObj.email=false
    
  }else if(regex.test(inputEmail)){
    
    // checkEmail.innerText = "사용가능한 이메일입니다."
    // checkEmail.style.color="green"
    // signUpCheckObj.email = true

    //*********************************** */
    //이메일 중복 검사
    $.ajax({
      url : "emailDupCheck",
      type : "GET",
      data : {"inputEmail":inputEmail}, //파라미터
      success : function(result){
        if(result == 0){
          updateCheckObj.email = true
        }else{
          alert("이미 사용중인 이메일입니다.")
          updateCheckObj.email = false

        }
      },
      error : function(request, status,error){
        // console.log(request)
        // console.log(status)
        if(request.status == 404){
          console.log("ajax 요청주소가 올바르지 않습니다.")
        } else if (request.status == 500){
          console.log("서버오류")
          console.log(request.responseText)

        }

      },
      complete : function(){

      }

    })
    
  }else{
    alert("유효하지 않은 이메일 주소입니다. ")
    updateCheckObj.email=false
  }
})


//수정 버튼 클릭 시 모든 값이 true가 아니면 submmit이벤트 무효
function memberUpdateValidate(){
  
  for(key in updateCheckObj){
    if(!updateCheckObj[key]){ //value가 false일 경우 

      let message 

      switch (key) {
        case "phone3": message="전화번호가 유효하지 않습니다."; break;
        case "email": message="이메일이 유효하지 않습니다."; break;
          
      }

      alert(message)
      document.getElementById(key).focus()

      return false;
    }
  }
}



