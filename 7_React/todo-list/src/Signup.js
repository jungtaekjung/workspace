import React, { useState } from 'react';

const SignupContainer = ()=>{
    const [id,setId] = useState('');
    const [pw,setPw] = useState('');
    const [pwConfirm,setPwConfirm] = useState('');
    const [name,setName] = useState('');
    const [result,setResult] = useState('');

    //아이디 중복 검사
    const [idValidation, setIdValidation]=useState(false);
    // false -> 사용 불가
    // true -> 사용 가능

    // 아이디 중복검사 함수
    const idCheck = (inputId) => {
        // inputId : 입력한 아이디
        setId(inputId)

        // (참고) user01 입력시
        console.log("id: " + id) // user0
        console.log("inputId: " + inputId) // user01
        // 차이나는 이유 : setState 함수는 전부 비동기 처리
        // -> 즉, setState 호출하면 값을 바로 변경하지 않고 대기
        // 이유 : 불필요한 렌더링을 방지하고, 성능 향상을 위해서 

        //4글자 미만 검사 X -> 빨간 글씨
        if(inputId.trim().length<4){
            setIdValidation(false);
            return;
        }

        // 중복 X -> 까만 글씨
        fetch("/idCheck?id="+ inputId)
        .then(resp=>resp.text())
        .then(result =>{

            console.log(`result : ${result}`)
            console.log(typeof result)
            if(Number(result) ===0){ // 중복 X ->까만 글씨
                setIdValidation(true);
            }else{         // 중복 -> 빨간 글씨
                setIdValidation(false);
            }
        })
        .catch(err=>console.log(err))






    }
    


    //회원 가입 함수
    const signup=()=>{

        //아이디가 사용 불가인 경우(4글자 미만 또는 중복)
        if(!idValidation){
            alert("아이디를 다시 입력해주세요.");
            return;
        }
        
        // 1. 비밀번호가 일치하지 않으면
        //    '비밀번호가 일치하지 않습니다.' alert 출력 후 return

        if(pw != pwConfirm){
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        // ** 회원 가입 요청(비동기,POST) **
        // 성공 시 : 회원 가입 성공
        // 실패 시 : 회원 가입 실패
        fetch("/signup",
            {
                method : "POST",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify({"id" : id,
                                    "pw":pw,
                                    "name": name
                })
            }
        )
        .then(resp=>resp.text())
        .then(result=> {
            if(result>0){
                setResult("회원 가입 성공");
                setId('');
                setPw('');
                setPwConfirm('');
                setName('');
            }else{
                setResult("회원 가입 실패");
            }
        })
        .catch(err=>console.log(err))


        
    }

        
    return(
    <div className='signup-container'>
        <label>
            ID : <input onChange={e=>{
            // {setId(e.target.value)}
               idCheck(e.target.value)
            }}
                                value={id}
                                className={idValidation ? '' : 'id-error'}/>
        </label>
        <br/>

        <label>
            PW : <input type='password' value={pw}
                        onChange={e=>setPw(e.target.value)}/>
        </label>
        <br/>
        <label>
            PW CONFIRM: <input type='password'value={pwConfirm}
                                    onChange={e=>{setPwConfirm(e.target.value)}}/>
        </label>
        <br/>

        <label>
            NAME : <input type='text' value={name}
                                onChange={e=>{setName(e.target.value)}}/>
        </label>
        <button onClick={signup}>가입하기</button>

        <hr/>

        <h3>{result}</h3>

        </div>
    );
};

export default SignupContainer;
