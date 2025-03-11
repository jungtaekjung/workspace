import './App.css';
import React, { useState, createContext } from 'react';
import SignupContainer from './Signup.js';
import Login from './Login';
import TodoList from './TodoList.js';

export const TodoListContext = createContext(); // 전역변수 생성



function App() {
  // 회원가입, 로그인 , 회원의 TodoList 출력 / 추가 / 제거 


  const [signupView,setSignupView] = useState(false);

  // 로그인한 회원 정보 저장
  const [loginMember, setLoginMember] = useState(null);

  // 로그인한 회원의 todo-list 저장
  const [todoList, setTodoList] = useState([]);


  return (
    <TodoListContext.Provider value={ {setTodoList, setLoginMember, loginMember, todoList} }>
      <button onClick={()=>{setSignupView(!signupView)}}>
        { signupView ?  ('회원 가입 닫기') : ( ' 회원 가입 열기')}
        </button>
        <div className='signup-wrapper'>
          {/* signupView가 true인 경우에만 회원 가입 컴포넌트 렌더링 */}
          {/* 조건식 && (true인 경우) */}
          {signupView === true && (<SignupContainer/>)}

        </div>
      <Login/>
      <hr/>
      {/* 로그인이 되어있는 경우에만 TodoList 출력 */}
     {loginMember && (<TodoList/>)} 
    </TodoListContext.Provider>
  );
}

export default App;
