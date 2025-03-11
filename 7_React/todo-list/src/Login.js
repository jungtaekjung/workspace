import React, { useState, useContext } from 'react';
import { TodoListContext } from './App';

const LoginComponent = () => {

    // 전역변수 context 사용
    const {setTodoList,setLoginMember,loginMember} = useContext(TodoListContext);

    const [id,setId] = useState('');
    const [pw,setPw] = useState('');

    const login = () =>{
 

     
        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ id: id, pw : pw })  
        })
        .then(resp => resp.json())
        .then((map) => {
            console.log(map)
            //로그인 성공 시 loginMember에 값 저장 + todoList 저장
            if(map.loginMember!==null){
                alert("로그인 성공")
                setLoginMember(map.loginMember);
                setTodoList(map.todoList);
                setId('');
                setPw('');
            }else{
                // 로그인 실패 시 '아이디 또는 비밀번호가 일치하지 않습니다'
                alert("아이디 또는 비밀번호가 일치하지 않습니다.")
            }
        })
        .catch(err => console.log(err));
        
    }

    const logout = () =>{
        {setLoginMember(null)};
    }

    return(
        <div className="login-container">
            
            {loginMember ? (  <button className="loginBtn" onClick={logout} >Logout</button>) : (
                
            <table>
            <tbody>
                <tr>
                    <th>ID</th>
                    <td>
                        <input type="text" onChange={e => setId(e.target.value)} value={id}/>
                    </td>
                </tr>

                <tr>
                    <th>PW</th>
                    <td>
                        <input type="password" value={pw} onChange={e => setPw(e.target.value)} />
                    </td>
                    <td>
                        <button className="loginBtn" onClick={login} >Login</button>
                    </td>
                </tr>
            </tbody>
        </table>

            )}

          
   
        </div>
    );
}

export default LoginComponent;