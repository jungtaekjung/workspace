import React from 'react';

const MainContent =() =>{
    return(
        <section className="main content">
        <section className="content-1"></section>
        <section className="content-2">
            <div>
                <fieldset className="id-pw-area">
                    <section>
                        <input type="text" name="memberEmail" placeholder="이메일"/>
                        <input type="password" name="memberPw" placeholder="비밀번호"/>                  
                    </section>
                    <section>
                        <button>로그인</button>
                    </section>
                </fieldset>
            </div>
        </section>
      </section>
    );
}

export default MainContent;