//각 입력 값이 유효선 검사를 진행했는지 기록할 객체
const signUpCheckObj={
  "id" : false,
  "name" : false,
  "email" : false,
  "pwd1":false,
  "pwd2":false,
  "phone3":false,
}

function validate(){
  //signUpCheckObj의 모든 값을 순차적으로 접근하여 false일 경우를 찾아냄
  for( key in signUpCheckObj){
    if(!signUpCheckObj[key]){
      //객체 속성 중 키가 key인 속성의 value 불러오기
      //논리 부정연산자로 조건이 참인지 확인하기

      let message

      switch(key){
        case "id" : message = "아이디가 유효하지 않습니다."; break;
        case "name" : message = "이름이 유효하지 않습니다."; break;
        case "email" : message = "유효하지 않은 이메일입니다."; break;
        case "pwd1" : message = "유효하지 않은 비밀번호입니다."; break;
        case "pwd2" : message = "비밀번호가 일치하지 않습니다."; break;
        case "phone3" : message = "전화번호가 유효하지 않습니다."; break;
      }

      alert(message)
      //유효하지 않은 input으로 포커스 이동
      document.getElementById(key).focus()

      //form 태그 submmit 제거
      return false
    }
  }
}

//아이디 유효성 검사 
// -영어 대/소문자 + 숫자, 총 6~12글자
document.getElementById("id").addEventListener("input", function(e){
  const inputId = this.value.trim()
  const regex = /^[a-zA-Z\d]{6,12}$/
  
  const checkId = document.getElementById("checkId")
  
  //유효성 검사
  if(inputId.length ==0){
    checkId.innerText=""
    signUpCheckObj.id=false
  }
  else if(regex.test(inputId)){
    // checkId.innerText="유효한 아이디 형식입니다."
    // checkId.style.color="green"
    // signUpCheckObj.id=true

    //***********************/
    //ajax를 이용한 id 중복확인 (비동기 통신)
    $.ajax({
      url: "idDupCheck", //요청 servlet주소 작성(필수 입력사항)
      data:{"inputId" : inputId}, //data : 요청시 전달할 Parameter 
      type: "GET", //데이터 전달 방식 (미작성시 GET)

      success : function(result){
        //비동기 통신 성공 시 수행(함수)
        //매개변수 result : 
        if(result == 0){
          checkId.innerText = "사용가능한 아이디 입니다."
          checkId.style.color = "green"
          signUpCheckObj.id = true;
          
        }else{
          checkId.innerText = "이미 사용중인 아이디 입니다."
          checkId.style.color = "red"
          signUpCheckObj.id = true;
        }
      },

      error : function(){
        //비동기 통신 중 에러발생 
      },

      complete : function(){

      }

    })


  }else{
    checkId.innerText="아이디 형식이 유효하지 않습니다."
    checkId.style.color="red"
    signUpCheckObj.id=false
  }

})


//이름 유효성 검사
// 한글 (자음+모음+받침)
document.getElementById("name").addEventListener("input", function(){
  const inputName = this.value
  const checkName = document.getElementById("checkName")

  const regex = /^[가-힣]{2,5}$/

  if(inputName.trim().length == 0){
    checkName.innerText =""
    signUpCheckObj.name=false
    
  }else if(regex.test(inputName)){
    checkName.innerText = "올바른 이름 형식입니다."
    checkName.style.color="green"
    signUpCheckObj.name=true
    
  }else{
    checkName.innerText = "올바른 이름 형식이 아닙니다."
    checkName.style.color="red"
    signUpCheckObj.name=false
  }
})

//이메일 유효성 검사
//const regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
document.getElementById("email").addEventListener("input", function(){

  const inputEmail = this.value.trim()
  const regex = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/
  const checkEmail = document.getElementById("checkEmail")
  
  if(inputEmail.length == 0){
    checkEmail.innerText ="이메일을 입력해 주세요"
    checkEmail.style.color ="black"
    signUpCheckObj.email=false
    
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
          checkEmail.innerText = "사용가능한 이메일입니다."
          checkEmail.style.color="green"
          signUpCheckObj.email = true
        }else{
          checkEmail.innerText = "이미 사용 중인 이메일입니다."
          checkEmail.style.color="red"
          signUpCheckObj.email = false

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
		//어떠한 경우에도 function을 수행
      }

    })
    
  }else{
    checkEmail.innerText = "이메일주소를 확인해 주세요."
    checkEmail.style.color="red"
    signUpCheckObj.email=false
  }
})

//비밀번호 유효성 검사 
//-영어 대/소문자, 숫자, 특수문자 6~20글자
document.getElementById("pwd1").addEventListener("input", function(){

  const inputPw = this.value.trim()
  const regex = /^[a-zA-Z\d\!\@\#\-\_]{6,20}$/
  const checkPwd1 = document.getElementById("checkPwd1")


  if(inputPw.length == 0){
    checkPwd1.innerText =""
    checkPwd1.style.color ="black"
    signUpCheckObj.pwd1=false
    
  }else if(regex.test(inputPw)){
    
    checkPwd1.innerText = "사용가능한 비밀번호입니다."
    checkPwd1.style.color="green"
    signUpCheckObj.pwd1 = true
    
  }else{
    checkPwd1.innerText = "비밀번호를 확인해 주세요."
    checkPwd1.style.color="red"
    signUpCheckObj.pwd1=false
  
  }
})

//비밀번호 중복 검사 ==> pwd1값과 같으면 유효 

$("#pwd2, #pwd1").on("input",function(){
  const pwd1 = document.getElementById("pwd1").value
  const pwd2 = document.getElementById("pwd2").value
  const checkPwd2 = document.getElementById("checkPwd2")

  if(pwd2.trim().length == 0){
    //비밀번호 확인이 빈칸일 경우
    checkPwd2.innerText = ""
    signUpCheckObj.pwd2= false

  }else if(pwd1==pwd2){
    checkPwd2.innerText="비밀번호가 일치합니다."
    checkPwd2.style.color="green"
    signUpCheckObj.pwd2 = true
    
  }else{
    checkPwd2.innerText="비밀번호가 일치하지 않습니다."
    checkPwd2.style.color="red"
    signUpCheckObj.pwd2 = false
  }
})

//글자수 제한 + 유효성검사 
$(".phone").on("input",function(){

  if($(this).val().length > 4){
    const num = $(this).val().slice(0,4) //인덱스 0부터 4미만

    //잘라서 4자리만 남은 값을 현재 입력중인 input태그의 value로 대입
    $(this).val(num)
  }

  //각각 입력된 번호 얻어오기 
  const inputPhone2 = document.getElementById("phone2").value
  const inputPhone3 = document.getElementById("phone3").value

  const regex2 = /^[\d]{3,4}$/ 
  const regex3 = /^[\d]{4}$/ 

  const checkPhone = document.getElementById("checkPhone")

  if(regex2.length == 0 && regex3.length == 0){
    checkPhone.innerText =""
    signUpCheckObj.phone3 =false

  }else if(regex2.test(inputPhone2) && regex3.test(inputPhone3) ){
    checkPhone.innerText = "유효한 전화번호 입니다."
    checkPhone.style.color ="green"
    signUpCheckObj.phone3=true
  }else{
    checkPhone.innerText = "유효하진 않은 전화번호 입니다."
    checkPhone.style.color ="red"
    signUpCheckObj.phone3=true

  }

})