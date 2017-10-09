<template>
  <li>
    <a href="#"><i> Back </i>
      <span class="nav-text">
        <b>{{content()}}
      </span>
    </a>
  </li>
</template>

<script>
import SidebarButton from '../components/SidebarButton';
export default {
  extends: SidebarButton
}
</script>
