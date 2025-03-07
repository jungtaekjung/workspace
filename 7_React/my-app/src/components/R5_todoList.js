import React, { useState } from 'react';

// useState 복습
const InputName = () =>{
    const [name, setName] = useState("");
    // state : 컴포넌트의 상태
    // useState : 컴포넌트의 상태를 관리
    //              -> state 변화가 감지되면 컴포넌트를 리렌더링

    const nameHandler = e => {
        setName(e.target.value);
    }
    return(
        <div>
            <span>이름 : </span>
            <input onChange={nameHandler}/>
            <h3>{name}</h3>
        </div>
    );
}

const TodoList = ()=>{

    // 할 일을 저장할 변수 -> 상태가 변하면 컴포넌트(TodoList) 리렌더링
    const [todos, setTodos] = useState([
        {text : '프로젝트 진행하기', completed : false},
        {text : '리액트 복습하기', completed : false}
    ]);
    console.log(todos);
    // 할 일 입력 컴포넌트
    const InputTodo = () => {
        const [inputText, setInputText] = useState('');

        // 버튼 클릭 시 할 일 추가
        const addTodo = () => {
            const newTodo = {
                text : inputText,
                completed : false

            }
            // todos에 newTodo를 추가한 객체배열 newTodos
            const newTodos = [...todos,newTodo];
        
            // 새로운 객체 배열 newTodos를 todos에 대입
            // -> 상태 변화 인식 -> 리렌더링 진행
            setTodos(newTodos);
        
        }
        return(
            <div>
                <h4>할 일 추가</h4>
                <input onChange={(e)=>{setInputText(e.target.value)}}/>
                <button onClick={addTodo}>추가하기</button>

            </div>
        )
    }

    // 체크박스 값 변경 시 
    const todoChange = index => {
        const newTodos = [...todos]; // todos를 풀어서 새로운 배열
                                     // 전개 연산자를 이용한 배열 깊은 복사
        // boolean값을 반대로 변경해서 대입
        newTodos[index].completed = !newTodos[index].completed;                             
        
        setTodos(newTodos);
    }

    const todoRemove = index =>{
        const todosCopy = [...todos];

        // 배열.splice(인덱스, 제거할 요소의 개수)
        todosCopy.splice(index,1);
        setTodos(todosCopy);

    }
    return(
        <>
            <InputName/>
            <hr/>

            <h1>Todo List</h1>

            {/* 할 일 입력 */}
            <InputTodo/>

            {/* 할 일 목록 */}
            {/* 배열.map((배열요소, 인덱스) => {return 값;} ) 
                -> 기존 배열을 이용해서 새로운 배열을 만드는 함수
                -> 새로운 배열의 요소는 map에서 return 되는 값으로 이루어짐
            
            */}
            <ul>
                {todos.map((todo, index)=>{
                    return (
                        <li key={index}>
                            <input type='checkbox' checked={todo.completed}
                                    onChange={()=>{todoChange(index)}}/>
                            <span className={todo.completed ? 'completed' : ''}>{todo.text}</span>
                            <button onClick={() => todoRemove(index)}>삭제</button>

                        </li>
                    );
                })}
            </ul>
        </>
    );

}

export default TodoList;
// 외부에서 해당 파일 import 시 TodoList 함수를 내보내는 기본값으로 지정