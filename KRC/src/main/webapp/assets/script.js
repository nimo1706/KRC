'use strict';

// ページリンクのリスト
const pageLinks = [
  {title:'Krctter', path:'./main', category:'Web Application'},
];

/**
 * html 読込時の処理
 */
window.addEventListener('load', (e) => {
  pageLinks.forEach((links, i) => {
    applyNavbar(links);
    applyCarousel(links, i);
  });
  applyUploadFileWait();
});

/**
 * ナビゲーションにページリンク設定
 * 
 * @param links
 *            ページリンク情報
 */
function applyNavbar(links) {
  if (!document.querySelector('#navbarCollapse')) return;

  let navItem = document.createElement('li');
  navItem.classList.add('nav-item');
  let navLink = document.createElement('a');
  navLink.classList.add('nav-link');
  navLink.innerText = links.title;
  navLink.href = links.path;
  document.querySelector('#navbarCollapse').querySelector('ul').appendChild(navItem);
  navItem.appendChild(navLink);
}

/**
 * カルーセルにページリンク設定
 * 
 * @param links
 *            ページリンク情報
 * @param index
 *            位置情報
 */
function applyCarousel(links, index) {
  if (!document.querySelector('#myCarousel')) return;

  let carouselBtn = document.createElement('button');
  carouselBtn.dataset.bsTarget = '#myCarousel';
  carouselBtn.dataset.bsSlideTo = index;
  if (index == 0) carouselBtn.classList.add('active');
  document.querySelector('#myCarousel').querySelector('.carousel-indicators').appendChild(carouselBtn);

  let carouselItem = document.createElement('div');
  carouselItem.classList.add('carousel-item');
  if (index == 0) carouselItem.classList.add('active');
  let carouselCaption = document.createElement('div');
  carouselCaption.classList.add('carousel-caption');
  let carouselCaptionTitle = document.createElement('h1');
  carouselCaptionTitle.innerText = [links.category, links.title].join(' ');
  let carouselCaptionDescription = document.createElement('p');
  carouselCaptionDescription.innerText = 'Comment to make it look like it. Anything is fine!';
  let carouselCaptionLink = document.createElement('a');
  carouselCaptionLink.classList.add('btn', 'btn-lg', 'btn-primary');
  carouselCaptionLink.innerText = links.title;
  carouselCaptionLink.href = links.path;
  document.querySelector('#myCarousel').querySelector('.carousel-inner').appendChild(carouselItem);
  carouselItem.appendChild(carouselCaption);
  carouselCaption.appendChild(carouselCaptionTitle);
  carouselCaption.appendChild(carouselCaptionDescription);
  carouselCaption.appendChild(carouselCaptionLink);
}

/**
 * アップロードファイル更新待ち設定
 */
function applyUploadFileWait() {
  document.querySelectorAll('.upload').forEach(uploadDom => {
    let dom = uploadDom.querySelector('.uploadFile');
    if (!dom || !['IMG', 'VIDEO', 'AUDIO'].includes(dom.tagName)) return;

    let domLoding = uploadDom.querySelector('.uploadFileLoading');
    dom.onerror = () => updateSrc(dom);
    if (dom.tagName == 'IMG') {
      dom.onload = () => loadSrc(dom, domLoding);
    } else {
      dom.onloadedmetadata = () => loadSrc(dom, domLoding);
    }

    dom.classList.add('d-none');
    domLoding.classList.remove('d-none');
    updateSrc(dom);
  });
}

/**
 * ファイル更新
 * 
 * @param dom
 *            メディアDOM(img, video など)
 */
function updateSrc(dom) {
  let src = dom.src;
  dom.src = '';
  dom.src = src;
}

/**
 * ファイル表示
 * 
 * @param dom
 *            メディアDOM(img, video など)
 * @param domLoding
 *            ローディングDOM
 */
function loadSrc(dom, domLoding) {
  domLoding.classList.add('d-none');
  dom.classList.remove("d-none");
}

/**
 * html 読込時の処理
 */
window.addEventListener('load', (e) => {

    // ねこ情報取得
    document.querySelector("#apicat").addEventListener("click", () => {
        fetch("https://aws.random.cat/meow")
            .then(response => response.json())
            .then(json => {
                console.log(json);
                document.querySelector("#apicat_json").value = JSON.stringify(json);
                document.querySelector("#apicat_img").src = json.file;
                document.querySelector("#apicat_img").height = 300;
            })
            .catch((error) => console.log(error));
    });

    // ユーザ情報取得
    document.querySelector("#apiuser").addEventListener("click", (e) => {
        fetch("https://randomuser.me/api")
            .then(response => response.json())
            .then(json => {
                console.log(json);
                document.querySelector("#apiuser_json").value = JSON.stringify(json);
                document.querySelector("#apiuser_img").src = json.results[0].picture.large;
                document.querySelector("#apiuser_img").height = 300;
            })
            .catch((error) => console.log(error));
    });

    // きつね情報取得
    document.querySelector("#apifox").addEventListener("click", (e) => {
        fetch("https://randomfox.ca/floof")
            .then(response => response.json())
            .then(json => {
                console.log(json);
                document.querySelector("#apifox_json").value = JSON.stringify(json);
                document.querySelector("#apifox_img").src = json.image;
                document.querySelector("#apifox_img").height = 300;
            })
            .catch((error) => console.log(error));
    });

    // 星座情報取得
    document.querySelector("#apistar").addEventListener("click", (e) => {
        fetch("https://livlog.xyz/hoshimiru/constellation?lat=35.605&lng=140.123&date=2023-1-18&hour=11&min=0")
            .then(response => response.json())
            .then(json => {
                console.log(json);
                document.querySelector("#apistar_json").value = JSON.stringify(json);
                document.querySelector("#apistar_img").src = json.result[0].starImage;
                document.querySelector("#apistar_img").height = 300;
            })
            .catch((error) => console.log(error));
    });

    // ポケモン情報取得
    document.querySelector("#apipokemon").addEventListener("click", (e) => {
        fetch("https://pokeapi.co/api/v2/pokemon/25")
            .then(response => response.json())
            .then(json => {
                console.log(json);
                document.querySelector("#apipokemon_json").value = JSON.stringify(json);
                document.querySelector("#apipokemon_img").src = json.sprites.front_default;
                document.querySelector("#apipokemon_img").height = 300;
            })
            .catch((error) => console.log(error));
    });

    // Servlet JSON 情報取得
    document.querySelector("#apijava1").addEventListener("click", (e) => {
        let type = document.querySelector("#apijava1_type").value;
        fetch("http://localhost:8080/java-wabapp-clump/api_work_json1?type=" + type)
            .then(response => response.json())
            .then(json => {
                console.log(json);
                document.querySelector("#apijava1_json").value = JSON.stringify(json);
                document.querySelector("#apijava1_namemsg").innerText = json.name + "：" + json.message;
            })
            .catch((error) => console.log(error));
    });

    // Servlet JSON 情報取得（Servlet で API実行した結果を返却）
    document.querySelector("#apijava2").addEventListener("click", (e) => {
        let type = document.querySelector("#apijava2_type").value;
        fetch("http://localhost:8080/java-wabapp-clump/api_work_json2?type=" + type)
            .then(response => response.json())
            .then(json => {
                console.log(json);
                document.querySelector("#apijava2_json").value = JSON.stringify(json);
                document.querySelector("#apijava2_img").src = json.image;
                document.querySelector("#apijava2_img").height = 300;
            })
            .catch((error) => console.log(error));
    });

});
