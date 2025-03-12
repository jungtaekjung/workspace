import React, { useState, useContext } from 'react';
import { TodoListContext } from './App';
import {faTrash} from '@fortawesome/free-solid-svg-icons'
import {faPlus} from '@fortawesome/free-solid-svg-icons'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'

const TodoList = () => {
    const {todoList, loginMember, setTodoList} = useContext(TodoListContext);

    const[inputTodo, setInputTodo] = useState('');

    // 할 일 추가
    const handleAddTodo = () =>{

        // 입력 X
        if(inputTodo.trim() === ''){
            alert("할 일을 입력해주세요.");
            return;
        }

        fetch("/todo", {
            method : "post",
            headers : {'Content-Type' : 'application/json'},
            body : JSON.stringify({
                title : inputTodo,
                todoMemberNo : loginMember.todoMemberNo
            })
        })
        .then(resp => resp.text())
        .then(todoNo => {
            if(Number(todoNo) === 0){return;} // 실패 시 종료

            // 기존 todoList + 새로 추가된 Todo를 이용해
            // 새 배열을 만들어 todoList에 대입

            // 새로 추가된 Todo 만들기
            const newTodo = {
                todoNo : todoNo,
                title : inputTodo,
                isDone : 'X',
                todoMemberNo : loginMember.todoMemberNo
            }

            // 기존 todoList + newTodo 이용해 새 배열 만들기
            const newTodoList = [...todoList, newTodo];

            // todoList에 대입
            setTodoList(newTodoList);
            setInputTodo('');

        })
        .catch(err => console.log(err))
    }

    // O,X 업데이트
    const handleToggleTodo = (todo, index) => {
        //console.log(todo);
        //console.log(index);8701

        fetch("/todo",{
            method : 'put',
            headers : {"Content-Type" : 'application/json'},
            body : JSON.stringify({
                todoNo : todo.todoNo,
                isDone : todo.isDone === 'O'? 'X' : 'O'
            })
        })
        .then(response => response.text())
        .then(result => {

            if(result === '0'){
                console.log('업데이트 실패');
                return;
            }

            // 수정 성공 시 todoList 값을 변경해서 리렌더링

            // todoList를 깊은 복사(똑같은 배열을 하나 더 만듦)
            const newTodoList = [...todoList];

            // index번째 요소의 O,X를 반대로 변경
            newTodoList[index].isDone 
                = newTodoList[index].isDone === 'O'? 'X' : 'O';

            setTodoList(newTodoList);

        })
        .catch(e => console.log(e));
    }


    // 삭제
    const handleDeleteTodo = (todoNo, index) => {
        //console.log(todoNo);
        //console.log(index);

        fetch("/todo", {
            method : "delete",
            headers : {'Content-Type' : 'application/json'},
            body : todoNo
        })
        .then(resp => resp.text())
        .then(result => {
            if(result === '0'){
                console.log('삭제 실패');
                return;
            }

            const newTodoList = [...todoList]; // 배열 복사

            // 배열.splice(인덱스, 몇칸)
            // -> 배열의 인덱스 번째 요소 부터
            //    몇칸을 잘라내서 반환할지 지정
            // --> 배열에서 잘라내진 부분이 사라짐
            newTodoList.splice(index, 1);
            setTodoList(newTodoList); // 리렌더링

        })
        .catch(e => console.log(e))
    }


    return(
        <main className="todo-container">
            <header>{loginMember.name}의 Todo List</header>

            <section className="addTodo">
                <FontAwesomeIcon icon={faPlus} onClick={handleAddTodo} />
                <input type="text"  placeholder={"Add Todo"} value={inputTodo}
                    onChange={e => setInputTodo(e.target.value)}/>
            </section>


            <section className="content">
                <ul className="todo-list">
                    {/* 배열.map : 기존 배열 이용해서 새로운 배열 만들기 */}
                    {todoList.map((todo, index)=>{
                        return(
                            <li key={todo.todoNo}>
                                <div>
                                    <span className={todo.isDone === 'O' ? 'todo-completed' : ''}>{todo.title}</span>
                                    <button onClick={() => { handleToggleTodo(todo, index) }} >{todo.isDone}</button>
                                    <FontAwesomeIcon icon={faTrash} onClick={() => { handleDeleteTodo(todo.todoNo, index) }} />
                                </div>
                            </li>
                        )
                        
                    })}
                </ul>
            </section>
        </main>
    );

}

export default TodoList;
