import React, {useState} from 'react';

const State2 = (props) => {

    // props : 부모로 부터 전달받은 값을 저장한 객체

    // const[count, setCount] = useState(0);
    const[count, setCount] = useState(props.init);

    // useState : 컴포넌트의 상태를 관리할 때 사용하는 Hook
    // const[변수, 값을 변경하는 함수(setter)] = useState(초기값);

    const handleClick = () => {
        setCount ( () => count +1)
    }

   
    return(
        <div>
            <h3>{count}</h3>
            <button onClick={handleClick}>클릭 시 1증가</button>
        </div>
    );
}

export default State2;
