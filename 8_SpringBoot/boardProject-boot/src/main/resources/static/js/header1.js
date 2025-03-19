console.log("헤더연결");

const query = document.getElementById("query")
const autocomplete=document.getElementById("autocomplete")

query.addEventListener("input",function(){

    if(query.value==""){
        autocomplete.innerHTML=""
        autocomplete.style.display = "none";
        return;
    }

    const searchText = query.value.trim();

    fetch(`/board/autocomplete?query=${searchText}`)
        .then(response => response.json()) 
        .then(data => {
            autocomplete.innerHTML = '';



            autocomplete.style.display = "block";
            for(let i=0; i<10; i++){
                
                let boardName=data.boardList[i].boardName
                let boardTitle=data.boardList[i].boardTitle
                let boardCode=data.boardList[i].boardCode
                let boardNo=data.boardList[i].boardNo
                console.log(boardName)
                console.log(boardTitle)

                const highlightedTitle = boardTitle.replace(new RegExp(searchText), (match) => `<span style="background-color: yellow;">${match}</span>`);
                const highlightedName = boardName.replace(new RegExp(searchText), (match) => `<span style="background-color: yellow;">${match}</span>`);
              

                const li= document.createElement("li");
                const a= document.createElement("a");
                a.innerHTML = `<span style="font-weight: bold;">${highlightedTitle}</span> - <span>${highlightedName}</span>`;
                a.setAttribute("href", `/board/${boardCode}/${boardNo}`);
                li.append(a)
                autocomplete.append(li)
                
            }
  
       
           
        
        })
        .catch(err => console.log(err)) //예외 처리
    
})