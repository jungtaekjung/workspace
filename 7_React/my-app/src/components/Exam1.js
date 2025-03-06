import React, { Component} from "react";

// 클래스형 컴포넌트 만들기
// 1. Component 상속 받기
// 2. render() 함수 작성
// 3. 만든 class를 export default 지정하기


class Exam1 extends Component{
    
    constructor(props) { 
        super(props); 
        this.state = { count: 0 }; 
    } 

    handleClick = () => { 
        this.setState({ count: this.state.count + 1 }); 
    } 


    // 화면 렌더링 시 render()함수의 반환된 값이 화면에 출력
    render(){
        return(
            <>
                <h2>클래스형 컴포넌트</h2>
                <h1>Count : {this.state.count}</h1>
                <button onClick={this.handleClick}>Increment</button>
            </>
        );
    }
}

export default Exam1;