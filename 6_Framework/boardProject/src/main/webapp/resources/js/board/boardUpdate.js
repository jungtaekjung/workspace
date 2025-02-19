console.log("boardUpdate.js연결");

//게시글 수정시 제목,내용 작성 여부 검사
    const boardTitle=document.getElementsByName("boardTitle")[0];
    const boardContent=document.getElementsByName("boardContent")[0];
    const boardUpdateFrm=document.getElementById("boardUpdateFrm");

//게시글 수정 시 삭제된 이미지의 순서를 기록할 set 생성
const deleteSet = new Set(); //중복X , 순서유지 X
// -> x버튼 클릭 시 순서를 한번만 저장하는 용도
const deleteList = document.getElementById('deleteList');

    boardUpdateFrm.addEventListener("submit",e=>{

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

        //input type="hidden" 태그에 deleteSet에 저장된 값을 "1,2,3"형태로 변경해서 저장

        // Array.from(deleteSet) : Set -> Array 변경
        // JS배열 특징 : string에 대입 되거나 출력 될 때 요소,요소 형태의 문자열로 반환
        document.getElementsByName("deleteList")[0].value=Array.from(deleteSet);

        
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

            //deleteSet에 삭제된 이미지 순서(i) 추가
            deleteSet.add(i);

        }

    })

}

 
