console.log("boardWrite.js연결");

//게시글 등록시 제목,내용 작성 여부 검사
    const boardTitle=document.getElementsByName("boardTitle")[0];
    const boardContent=document.getElementsByName("boardContent")[0];
    const boardWriteFrm=document.getElementById("boardWriteFrm");

    boardWriteFrm.addEventListener("submit",e=>{

        if(boardTitle.value.trim().length==0){
            alert("제목을 입력해주세요");
            boardTitle.focus();
            boardTitle.value="";
            e.preventDefault();
            return;
        }
    
        if(boardContent.value.trim().length==0){
            alert("내용을 입력해주세요");
            boardContent.focus();
            boardContent.value="";
            e.preventDefault();
            return;
        }
        
    })

    


// 사진 미리보기

const inputImage = document.getElementsByClassName("inputImage");
const deleteImage = document.getElementsByClassName("delete-image");
const preview = document.getElementsByClassName("preview");




for(let i=0; i<inputImage.length; i++){
    inputImage[i].addEventListener("change",e=>{
        const file=e.target.files[0]; //선택된 파일의 데이터

        if(file != undefined){ //파일이 선택 되었을 때

            const reader = new FileReader();
            reader.readAsDataURL(file);
            // 지정된 파일을 읽은 후 result 변수에 url 형식으로 저장
            reader.onload = e=>{
                preview[i].setAttribute("src", e.target.result)
               
            }
        }else{ //선택 후 취소되었을 때 == 선택된 파일 없음 -> 미리보기 삭제
            preview[i].removeAttribute("src");
        }

    })

}




// 미리보기 삭제(x버튼)
for(let i=0; i<deleteImage.length;i++){

    deleteImage[i].addEventListener("click",()=>{

        //미리보기 이미지가 있을 경우
        if(preview[i].getAttribute("src")!=''){
            //미리보기 삭제
            preview[i].removeAttribute("src");
            // file 태그의 value 삭제
            // *input type=file'의 value는 ''(빈칸)만 대입 가능!*
            inputImage[i].value = '';

        }

    })

}

