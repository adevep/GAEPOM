<template>
    <div class="projectTrackingDetail">
        <tracking-detail/>
        <comment/>
    </div>
</template>
<script>
import TrackingDetail from '@/components/projectTracking/TrackingDetail.vue';
import Comment from '@/components/projectTracking/comment/Comment.vue';

export default {
    name: "ProjectTrackingDetail",
    components: {
        TrackingDetail,
        Comment,
    }
}
</script>