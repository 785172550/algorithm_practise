const width = 1200;
const height = 600;

const svg = d3.select('#frame')
  .append('rect')
  .attr('height', height)
  .attr('width', width)
  // .style()
  .attr('fill', 'red');


export function sortData(data, desc) {
  data.sort((a, b) => {
    if (desc) {
      if (Number(a) < Number(b))
        return -1;
      else return 1;
    } else {
      if (Number(a) < Number(b))
        return 1;
      else -1;
    }
  })
}