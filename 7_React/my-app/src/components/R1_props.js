import React from "react";

// props : 부모 컴포넌트가 자식 컴포넌트에게 데이터 전달 시 사용하는 객체
// ** props는 자식 -> 부모 데이터 전달 불가능!


const ChildComponent = (props) => {
    return(
        <>
            <ul>
                <li>이름 : {props.name}</li>
                <li>나이 : {props.age}</li>
            </ul>
        </>
    );
}


const PropsEx = (props) => {
    // props 매개변수 : 부모로부터 전달 받은 값이 담겨져있는 객체
    console.log(props);
    console.log(props.name);

    const menu = {'떡볶이' : 3000, "돈가스" : 7000};
    return(
        <>
            <h1>{props.name}</h1>
            <ChildComponent name={props.name} age={props.name === '카리나' ? 27 : 33}/>
            <MenuPrint {...menu} />
        </>
    );
}

const MenuPrint = (props) => {
    return(
        <h4>떡볶이 : {props.떡볶이}/ 돈까스 : {props.돈가스}</h4>
    )

}
export default PropsEx;