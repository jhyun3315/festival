<template>
  <table class="table tablesorter" :class="tableClass">
    <thead :class="theadClasses">
      <tr>
        <slot name="columns" :columns="columns">
          <th v-for="column in columns" :key="column">{{ column }}</th>
        </slot>
      </tr>
    </thead>
    <tbody :class="tbodyClasses">
      <tr v-for="(item, index) in data" :key="index">
        <slot :row="item" :index="index">
          <td
            v-for="(column, index) in columns"
            :key="index"
            v-if="hasValue(item, column)"
          >
            {{ itemValue(item, column) }}
          </td>
        </slot>
      </tr>
    </tbody>
  </table>
</template>
<script>
import api from '@/util/api';

export default {
  name: 'base-table',
  props: {
    columns: {
      type: Array,
      default: () => [], 
    },
    data: {
      type: Array,
      default: () => [], 
    },
    type: {
      type: String, // striped | hover
      default: '', 
    },
    theadClasses: {
      type: String,
      default: '', 
    },
    tbodyClasses: {
      type: String,
      default: '', 
    }
  },
  computed: {
    tableClass() {
      return this.type && `table-${this.type}`;
    }
  },
  methods: {
    getData(d) {
      
    },

    hasValue(item, column) {
      return item[column.toLowerCase()] !== 'undefined';
    },
    itemValue(item, column) {
      return item[column.toLowerCase()];
    }
  }
};
</script>
<style></style>
