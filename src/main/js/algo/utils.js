const width = 1200;
const height = 600;

const svg = d3.select('#frame')
  .append('rect')
  .attr('height', height)
  .attr('width', width)
  // .style()
  .attr('fill', 'red');


export function sortData(data, desc) {
  data.sort((a, b) => desc ? b - a : a - b)
}