"use strict";

(function() {
  window.addEventListener("load", init);

  function init() {
    let menuIcon = document.querySelector("#menu-icon");
    let secondMenu = document.querySelector("#second-menu");

    menuIcon.onclick = () => {
      secondMenu.classList.toggle('active');
    };

    window.onscroll = () => {
      secondMenu.classList.remove('active');
    };

    id("terraluna-btn").addEventListener("click", showVideo);
    id("allocation-btn").addEventListener("click", showFiles);
  }

  function showVideo() {
    const links = id("java-downloads");
    links.classList.add('hidden');

    const video = id("terraluna-video");
    if (video.classList.contains('hidden')) {
      video.classList.remove('hidden');
    } else {
      video.classList.add('hidden');
    }
  }

  function showFiles() {
    const video = id("terraluna-video");
    video.classList.add('hidden');
    id("video-container").classList.add("hidden");

    const links = id("java-downloads");
    links.classList.remove('hidden');
  }

  function id(id) {
    return document.getElementById(id);
  }

  function qs(selector) {
    return document.querySelector(selector);
  }

  function qsa(selector) {
    return document.querySelectorAll(selector);
  }
})();