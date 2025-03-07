import './App.css';

// components폴더의 Exam1.js를 가져와서 사용
// 사용할 이름을 Ex1으로 지정
import Ex1 from './components/Exam1'; // .js 생략가능
import Ex2 from './components/Exam2'; // .js 생략가능
import PropsEx from './components/R1_props'; // .js 생략가능
import State1 from './components/R2_state1'; // .js 생략가능
import State2 from './components/R3_state2'; // .js 생략가능
import State3 from './components/R4_state3'; // .js 생략가능
import TodoList from './components/R5_todoList'; // .js 생략가능
import ContextApi from './components/R6_contextApi'; // .js 생략가능


function App() {
  // 리액트의 컴포넌트는 딱 하나의 요소만 반환 가능
  // 여러 요소를 반환하고 싶은 경우 부모 요소로 감싸준다
  // -> fragment(<></>) : 반환되는 요소를 감쌀 때 사용, 해석X
  return (
    <>
    {/* jsx 주석 */}

    {/* <h1 className='temp' style={ {color : 'red', fontSize : '20px'} }>Hello React</h1> */}
      {/* inline 방식으로 style 추가 시 : style ={ { 스타일명 : '값' } }  */}

    {/* <div>리액트 첫걸음</div> */}

    {/* <Ex1 /> */}
    {/* <Ex2 /> */}
    {/* <PropsEx name={'카리나'}/> */}
    {/* <PropsEx name={'닝닝'}/> */}
    {/* <State1/> */}
    {/* {<State2 init={50}/>} */}
    {/* <State3/> */}
    {/* <TodoList/> */}
    <ContextApi/>
    </>
  );
}

export default App;
