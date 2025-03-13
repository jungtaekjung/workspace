import './App.css';
import logo from './images/logo.jpg';
import {Routes, Route,Link} from 'react-router-dom';
import MainContent from './routes/MainContent';
import BoardList from './routes/BoardList';
import React, {createContext, useState} from 'react';
import AddBoardList from './routes/AddBoardList';
import BoardDetail from './routes/BoardDetail';


export const DataContext = createContext(); // 전역변수 생성

function App() {

  const [data, setData] = useState([
    {title : "강남 놀거리 추천!", writer : '유저일', like : 0, issueDate : '2025-03-12', updateDate : '', content : '추천 받아요~' },
    {title : "리액트 마스터 하는 법", writer : '유저이', like : 0, issueDate : '2025-03-13',  updateDate : '', content : '이해될 때까지 복습'},
    {title : "바디프로필 100일만에 찍는 법", writer : '유저삼', like : 0, issueDate : '2025-03-14',  updateDate : '', content : '덜먹고 더운동하기'},
  ])

  return (
    <DataContext.Provider value={ {data,setData}}>
      <main>
          <header>
              <section>
                {/* Link : a 태그 역할
                  -a태그와의 차이점 : 페이지를 새로 불러오지 않음
                */}
                  <Link to="/">
                      <img src={logo} id="homeLogo"/>
                  </Link>
              </section>


              <section></section>
              <section></section>
          </header>


          <nav>
              <ul>
                  <li><Link to="/0">공지사항</Link></li>
                  <li><Link to="/1">자유 게시판</Link></li>
                  <li><Link to="/2">질문 게시판</Link></li>
                  <li><Link to="/3">FAQ</Link></li>
                  <li><Link to="/4">1:1문의</Link></li>
              </ul>
          </nav>

          <Routes>
            {/* 
              :URL 파라미터
               path: 요청 주소
               element : 보여질 화면 (컴포넌트)
            */}
            <Route path="/" element={<MainContent/>} />
            <Route path="/:boardCode" element={<BoardList/>}>
            {/* 게시글 목록 추가 */}
            {/* nested routes : 중첩으로 route 작성 
                -> Outlet 태그를 이용하여 보여지게될 위치 지정해야함*/}
              <Route path="addBoardList" element={<AddBoardList/>}/>

            </Route>

              {/* 게시글 상세 화면 */}
              <Route path="/:boardCode/detail/:boardNo" element={<BoardDetail/>}/>
              
              {/* 잘못된 요청인 경우 */}
              <Route path = '*' element={<div className='error-page'>존재하지 않는 페이지입니다.</div>}/>
          </Routes>


     
         
      </main>
    </DataContext.Provider>
  );
}


export default App;
