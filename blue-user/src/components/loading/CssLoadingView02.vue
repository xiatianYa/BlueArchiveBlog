<template>
      <div class="loader"></div>
</template>
<style lang="scss" scoped>
/* HTML: <div class="loader"></div> */
.loader {
      width: 16px;
      aspect-ratio: 1;
      background:
            radial-gradient(farthest-side at bottom right, #F10C49 90%, #0000),
            radial-gradient(farthest-side at bottom left, #F10C49 90%, #0000),
            radial-gradient(farthest-side at top right, #F10C49 90%, #0000),
            radial-gradient(farthest-side at top left, #F10C49 90%, #0000),
            radial-gradient(farthest-side at bottom right, #f4dd51 90%, #0000),
            radial-gradient(farthest-side at bottom left, #f4dd51 90%, #0000),
            radial-gradient(farthest-side at top right, #f4dd51 90%, #0000),
            radial-gradient(farthest-side at top left, #f4dd51 90%, #0000),
            radial-gradient(farthest-side at bottom right, #e3aad6 90%, #0000),
            radial-gradient(farthest-side at bottom left, #e3aad6 90%, #0000),
            radial-gradient(farthest-side at top right, #e3aad6 90%, #0000),
            radial-gradient(farthest-side at top left, #e3aad6 90%, #0000);
      background-size: 25% 25%;
      background-repeat: no-repeat;
      animation: l16 2s infinite;
}

@keyframes l16 {
      0% {
            background-position:
                  calc(1*100%/3) calc(1*100%/3), calc(2*100%/3) calc(1*100%/3), calc(1*100%/3) calc(2*100%/3), calc(2*100%/3) calc(2*100%/3),
                  calc(1*100%/3) calc(1*100%/3), calc(2*100%/3) calc(1*100%/3), calc(1*100%/3) calc(2*100%/3), calc(2*100%/3) calc(2*100%/3),
                  calc(1*100%/3) calc(1*100%/3), calc(2*100%/3) calc(1*100%/3), calc(1*100%/3) calc(2*100%/3), calc(2*100%/3) calc(2*100%/3)
      }

      16.67% {
            background-position:
                  calc(0*100%/3) calc(0*100%/3), calc(3*100%/3) calc(0*100%/3), calc(0*100%/3) calc(3*100%/3), calc(3*100%/3) calc(3*100%/3),
                  calc(1*100%/3) calc(1*100%/3), calc(2*100%/3) calc(1*100%/3), calc(1*100%/3) calc(2*100%/3), calc(2*100%/3) calc(2*100%/3),
                  calc(1*100%/3) calc(1*100%/3), calc(2*100%/3) calc(1*100%/3), calc(1*100%/3) calc(2*100%/3), calc(2*100%/3) calc(2*100%/3)
      }

      33.33% {
            background-position:
                  calc(0*100%/3) calc(0*100%/3), calc(3*100%/3) calc(0*100%/3), calc(0*100%/3) calc(3*100%/3), calc(3*100%/3) calc(3*100%/3),
                  calc(0*100%/3) calc(0*100%/3), calc(3*100%/3) calc(0*100%/3), calc(0*100%/3) calc(3*100%/3), calc(3*100%/3) calc(3*100%/3),
                  calc(1*100%/3) calc(1*100%/3), calc(2*100%/3) calc(1*100%/3), calc(1*100%/3) calc(2*100%/3), calc(2*100%/3) calc(2*100%/3)
      }

      50% {
            background-position:
                  calc(0*100%/3) calc(0*100%/3), calc(3*100%/3) calc(0*100%/3), calc(0*100%/3) calc(3*100%/3), calc(3*100%/3) calc(3*100%/3),
                  calc(0*100%/3) calc(0*100%/3), calc(3*100%/3) calc(0*100%/3), calc(0*100%/3) calc(3*100%/3), calc(3*100%/3) calc(3*100%/3),
                  calc(0*100%/3) calc(0*100%/3), calc(3*100%/3) calc(0*100%/3), calc(0*100%/3) calc(3*100%/3), calc(3*100%/3) calc(3*100%/3)
      }

      66.67% {
            background-position:
                  calc(1*100%/3) calc(1*100%/3), calc(2*100%/3) calc(1*100%/3), calc(1*100%/3) calc(2*100%/3), calc(2*100%/3) calc(2*100%/3),
                  calc(0*100%/3) calc(0*100%/3), calc(3*100%/3) calc(0*100%/3), calc(0*100%/3) calc(3*100%/3), calc(3*100%/3) calc(3*100%/3),
                  calc(0*100%/3) calc(0*100%/3), calc(3*100%/3) calc(0*100%/3), calc(0*100%/3) calc(3*100%/3), calc(3*100%/3) calc(3*100%/3)
      }

      83.33% {
            background-position:
                  calc(1*100%/3) calc(1*100%/3), calc(2*100%/3) calc(1*100%/3), calc(1*100%/3) calc(2*100%/3), calc(2*100%/3) calc(2*100%/3),
                  calc(1*100%/3) calc(1*100%/3), calc(2*100%/3) calc(1*100%/3), calc(1*100%/3) calc(2*100%/3), calc(2*100%/3) calc(2*100%/3),
                  calc(0*100%/3) calc(0*100%/3), calc(3*100%/3) calc(0*100%/3), calc(0*100%/3) calc(3*100%/3), calc(3*100%/3) calc(3*100%/3)
      }

      100% {
            background-position:
                  calc(1*100%/3) calc(1*100%/3), calc(2*100%/3) calc(1*100%/3), calc(1*100%/3) calc(2*100%/3), calc(2*100%/3) calc(2*100%/3),
                  calc(1*100%/3) calc(1*100%/3), calc(2*100%/3) calc(1*100%/3), calc(1*100%/3) calc(2*100%/3), calc(2*100%/3) calc(2*100%/3),
                  calc(1*100%/3) calc(1*100%/3), calc(2*100%/3) calc(1*100%/3), calc(1*100%/3) calc(2*100%/3), calc(2*100%/3) calc(2*100%/3)
      }
}
</style>
