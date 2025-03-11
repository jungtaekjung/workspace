import React, { useState, useContext } from 'react';
import {TodoListContext} from './App';
import {faTrash} from '@fortawesome/free-solid-svg-icons'
import {faPlus} from '@fortawesome/free-solid-svg-icons'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'


const TodoList = () => {

    const {todoList, loginMember,setTodoList} = useContext(TodoListContext);
    const [inputTodo, setInputTodo] = useState('');

    // 할 일 추가
    const handleAddTodo = () =>{
        //입력 X
        if(inputTodo.trim()===''){
            alert("할 일을 입력해주세요.");
            return;
        }
        fetch("/todo",{
            method : "POST",
            headers : {'Content-Type' : 'application/json'},
            body : JSON.stringify({
                title : inputTodo,
                todoMemberNo : loginMember.todoMemberNo

            })
        })
        .then(resp => resp.text())
        .then(todoNo =>{
            if(Number(todoNo)===0){return;} // 실패 시 종료

            //기존 todoList + 새로 추가된 Todo를 이용해
            // 새 배열을 만들어 todoList에 대입

            //새로 추가된 Todo 만들기
            const newTodo = {
                todoNo : todoNo,
                title : inputTodo,
                isDone : 'X',
                todoMemberNo : loginMember.todoMemberNo
            }
            // 기존 todoList+newTodo 이용해 새 배열 만들기
            const newTodoList = [...todoList,newTodo];
            // todoList에 대입
            setTodoList(newTodoList);
            setInputTodo('');
        })
        .catch(err => console.log(err))
    }

    const handleDeleteTodo=(todo,index) =>{
        fetch("/todo", {
            method: "DELETE",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(todo.todoNo) 
        })
        .then(resp => resp.text())
        .then(result =>{
            if(Number(result)===0){return;} 
            const todosCopy = [...todoList];

            todosCopy.splice(index,1);
            setTodoList(todosCopy);
        })
        .catch(err => console.log(err))

    }

    const handleUpdateTodo=(todo,index) =>{
        const newStatus = todo.isDone === 'X' ? 'O' : 'X';
        fetch("/todo", {
            method: "PUT",
            headers: {'Content-Type': 'application/json'},
            body : JSON.stringify({
                isDone : newStatus,
                todoNo : todo.todoNo
            })
        })
        .then(resp => resp.text())
        .then(result =>{
            console.log(result)
            const newTodoList = [...todoList];
            newTodoList[index] = { ...newTodoList[index], isDone: newStatus };
            setTodoList(newTodoList);
        
        })
        .catch(err => console.log(err))

    }



    return(
        <main className="todo-container">
            <header>{loginMember.name}의 Todo List</header>


            <section className="addTodo">
                <FontAwesomeIcon icon={faPlus} onClick={handleAddTodo}  />
                <input type="text"  placeholder={"Add Todo"} value={inputTodo}
                        onChange={e=>setInputTodo(e.target.value)} />
            </section>


            <section className="content">
                <ul className="todo-list">
                    {/* 배열.map : 기존 배열 이용해서 새로운 배열 만들기 */}
                   {todoList.map((todo,index) => (
                        <li key={todo.todoNo}>
                            <div>
                                <span>{todo.title}</span>  
                                <button onClick={() => handleUpdateTodo(todo,index)} >{todo.isDone}</button>   
                                <FontAwesomeIcon icon={faTrash} onClick={() =>handleDeleteTodo(todo,index)} />
                            </div>
                        </li>
                    ))}
                </ul>
            </section>
        </main>
    );

   
}

export default TodoList;

