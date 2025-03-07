import React, { useState, useContext } from 'react';
import UserContext from '../contexts/UserContext';

const User = () => {
    
    const {temp, user} = useContext(UserContext); // user, temp
    // useContext(Context명) : 지정된 Context를 사용
    // -> 부모 컴포넌트에서 제공한 값을 꺼내쓸 수 있다.

    // UserContext에서 user를 꺼내서 변수 user에 저장
    
    console.log(user)
    console.log(temp)
    return(
        <ul>
            <li>{user.name}</li>
            <li>{user.email}</li>
        </ul>
    );
}

const Profile = () => {

    const [user, setUser] = useState(null);
    const print = ()=>{
        setUser({name : '홍길동', email: 'hong@kh.or.kr'})
    }
    const hide = () =>{
        setUser(null);
    }

    const temp = '임시 변수';
    return(
        // UserContext가 감싸고 있는 자식 컴포넌트에게 Context API를 이용해서 user를 제공
        <UserContext.Provider value={{user, temp}}>
            
            {/* 삼항 연산자를 이용한 컴포넌트 렌더링 제어 */}
            {user !=null ? (
                <>
                <button onClick={hide}>개인 정보 숨기기</button>
                <User/>
                </>

            ) : (

                <button onClick={print}>개인 정보 출력</button>
            )}
        </UserContext.Provider>
    );
}

export default Profile;
